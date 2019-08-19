package com.library.chat.core.config.jpa.support;

import com.library.chat.core.config.jpa.enums.QueryType;
import com.library.chat.core.config.jpa.specs.DynamicSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.provider.PersistenceProvider;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * ClassName: BaseJpaRepositoryImpl
 * @Description: 公共基础Repository 实现类
 * @param <T>
 * @param <ID>
 * @author feifei.liu
 * @date 2015年9月9日 下午1:04:26
 */
public class BaseJpaRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseJpaRepository<T, ID> {

    private final JpaEntityInformation<T, ?> entityInformation;
    private final EntityManager em;
    private final PersistenceProvider provider;
    private CrudMethodMetadata metadata;

    public BaseJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        Assert.notNull(entityInformation, "JpaEntityInformation must not be null!");
        Assert.notNull(entityManager, "EntityManager must not be null!");
        this.entityInformation = entityInformation;
        this.em = entityManager;
        this.provider = PersistenceProvider.fromEntityManager(entityManager);
    }

    public BaseJpaRepositoryImpl(Class<T> domainClass, EntityManager em) {
        this(JpaEntityInformationSupport.getEntityInformation(domainClass, em), em);
    }

    public Class<T> getDomainClass() {
        return super.getDomainClass();
    }

    /* (non-Javadoc)
     * <p>Title: rollback</p>
     * <p>Description: 事务缓存</p>
     * @see com.baihui.core.jpa.support.BaseJpaRepository#rollback()
     */
    @Override
    public void flush(){
        super.flush();
    }

    /* (non-Javadoc)
    * <p>Title: clear</p>
    * <p>Description: </p>
    * @see com.baihui.core.jpa.support.BaseJpaRepository#clear()
    */
    public void clear() {
        em.clear();
    }

    /* (non-Javadoc)
     * <p>Title: rollback</p>
     * <p>Description: 事务回滚</p>
     * @see com.baihui.core.jpa.support.BaseJpaRepository#rollback()
     */
    public void rollback(){
        em.getTransaction().rollback();
    }

    @Override
    public T getOne(ID id) {
        return super.getOne(id);
    }

    public <S> S getOne(Class<S> clazz, ID id) {
        return em.getReference(clazz, id);
    }

    @Override
    public T findOne(ID id) {
        return super.findOne(id);
    }

    public <S> S findOne(Class<S> clazz, ID id) {
        return em.find(clazz, id);
    }

    @Transactional
    public <S extends T> S save(S entity) {
        return super.save(entity);
    }

    public <S> S persist(S entity) {
        this.em.persist(entity);
        return entity;
    }

    public <S> S merge(S entity) {
        return em.merge(entity);
    }

    public List<T> findAll() {
        return super.findAll();
    }

    /* (non-Javadoc)
    * <p>Title: findAll</p>
    * <p>Description: 根据HQL语句获取查询结果集合</p>
    * @param queryString
    * @return
    * @see com.baihui.core.jpa.support.BaseJpaRepository#findAll(java.lang.String)
    */
    public List findAll(String queryString) {
        return em.createQuery(queryString).getResultList();
    }

    /* (non-Javadoc)
    * <p>Title: findAll</p>
    * <p>Description: 分页-HQL语句</p>
    * @param queryString
    * @param pageable
    * @return
    * @see com.baihui.core.jpa.support.BaseJpaRepository#findAll(java.lang.String, org.springframework.data.domain.Pageable)
    */
    @SuppressWarnings("unchecked")
    public Page<T> findAll(String queryString, Pageable pageable){
        int pageSize=pageable.getPageSize();
        int pageNum=pageable.getPageNumber();
        int startIndex=pageNum*pageSize;
        Query query=em.createQuery(queryString);
        long total= Long.valueOf(em.createQuery("select count(*) "+queryString.substring(queryString.indexOf("from"))).getResultList().get(0).toString());
        query.setFirstResult(startIndex).setMaxResults(pageSize);
        List<T> content=query.getResultList();
        return new PageImpl<T>(content, pageable, total);
    }

    public List<T> findAll(String queryString, Pageable pageable, Specification<T> spec){
        return null;
    }

    /* (non-Javadoc)
    * <p>Title: findBySql</p>
    * <p>Description: 根据SQL语句获取查询结果集合</p>
    * @param sqlString
    * @return
    * @see com.baihui.core.jpa.support.BaseJpaRepository#findBySql(java.lang.String)
    */
    @SuppressWarnings("rawtypes")
    public List findBySql(String sqlString) {
        Query query=em.createNativeQuery(sqlString);
        return query.getResultList();
    }

    /* (non-Javadoc)
    * <p>Title: findBySql</p>
    * <p>Description: 根据SQL语句、实体类获取查询结果集合</p>
    * @param sqlString
    * @param resultClass
    * @return
    * @see com.baihui.core.jpa.support.BaseJpaRepository#findBySql(java.lang.String, java.lang.Class)
    */
    public List findBySql(String sqlString, Class<?> clazz){
        Query query=em.createNativeQuery(sqlString, clazz);
        return query.getResultList();
    }

    /* (non-Javadoc)
    * <p>Title: findBySql</p>
    * <p>Description:  根据SQL语句、resultSetMapping获取查询结果集合</p>
    * @param sqlString
    * @param resultSetMapping
    * @return
    * @see com.baihui.core.jpa.support.BaseJpaRepository#findBySql(java.lang.String, java.lang.String)
    */
    @SuppressWarnings("rawtypes")
    public List findBySql(String sqlString,String resultSetMapping){
        Query query=em.createNativeQuery(sqlString, resultSetMapping);
        return query.getResultList();
    }

    /* (non-Javadoc)
    * <p>Title: findBySql</p>
    * <p>Description: 根据SQL语句查询数据，并返回计数</p>
    * @param sqlString
    * @param start
    * @param length
    * @return
    * @see com.baihui.core.jpa.support.BaseJpaRepository#findBySql(java.lang.String, int, int)
    */
    public Map<QueryType, Object> findBySql(String sqlString, int start, int length){
        Map<QueryType, Object> resultMap=new LinkedHashMap<QueryType, Object>();
        //查询数据
        Query query=em.createNativeQuery(sqlString);
        query.setFirstResult(start).setMaxResults(length);
        List data = this.findBySql(sqlString+" limit "+length+" OFFSET "+start);
        resultMap.put(QueryType.total, data.size()+start+length);
        //统计总数
        String fromSql = sqlString.substring(sqlString.indexOf("from", 0));
        boolean matches = Pattern.compile("^from\\s+(s_|bh_settlement|bh_basic_unit_group|bh_attention_rule).*", Pattern.CASE_INSENSITIVE).matcher(fromSql).find();
        if(matches){
            String countSQL="select count(*) "+fromSql;
            if(sqlString.indexOf("order by")!=-1) countSQL="select count(*) "+sqlString.substring(sqlString.indexOf("from", 0),sqlString.indexOf("order by", 0));
            BigInteger total=(BigInteger) em.createNativeQuery(countSQL).getResultList().get(0);
            resultMap.put(QueryType.total, total);
        }else{
            resultMap.put(QueryType.total, data.size()+start+length);
        }
        resultMap.put(QueryType.data, data);
//		resultMap.put(QueryType.data, query.getResultList());
        return resultMap;
    }

    public Query createQuery(String qlString) {
        return em.createQuery(qlString);
    }

    public Query createQuery(CriteriaUpdate updateQuery) {
        return em.createQuery(updateQuery);
    }

    public Query createQuery(CriteriaDelete deleteQuery) {
        return em.createQuery(deleteQuery);
    }

    public TypedQuery createQuery(CriteriaQuery criteriaQuery) {
        return em.createQuery(criteriaQuery);
    }

    public TypedQuery createQuery(String qlString, Class<?> resultClass) {
        return em.createQuery(qlString,resultClass);
    }

    /**
     * @Description: 执行SQL语句返回Query对象
     * @param sqlString
     * @return
     * @return Query
     * @throws
     * @author feifei.liu
     * @date 2016年6月2日 下午1:49:07
     */
    public Query createNativeQuery(String sqlString) {
        return em.createNativeQuery(sqlString);
    }

    public Query createNativeQuery(String sqlString, Class<?> clazz) {
        return em.createNativeQuery(sqlString, clazz);
    }

    public Query createNativeQuery(String sqlString, String resultSetMapping) {
        return em.createNativeQuery(sqlString, resultSetMapping);
    }

    /**
     * @Description: 执行SQL
     * @param sqlString
     * @param objects
     * @return int
     * @throws
     * @author chong.cheng
     * @date 2017年5月12日 下午12:08:21
     */
    public int execNativeQuery(String sqlString,Object...objects){
        Query query = em.createNativeQuery(sqlString);
        if(objects!=null&&objects.length>0){
            for(int i=0;i<objects.length;i++){
                query.setParameter(i+1,objects[i]);
            }
        }
        return query.executeUpdate();
    }

    /**
     * @Description: 存储过程调用
     * @param procedureName
     * @return
     * @return StoredProcedureQuery
     * @throws
     * @author feifei.liu
     * @date 2016年6月1日 下午9:23:18
     */
    public StoredProcedureQuery createStoredProcedureQuery(String procedureName){
        return em.createStoredProcedureQuery(procedureName);
    }

    public StoredProcedureQuery createStoredProcedureQuery(String procedureName, Class<?>... resultClasses){
        return em.createStoredProcedureQuery(procedureName, resultClasses);
    }

    public StoredProcedureQuery createStoredProcedureQuery(String procedureName, String... resultSetMappings){
        return em.createStoredProcedureQuery(procedureName, resultSetMappings);
    }

    /**
     * @Description: 执行SQL语句
     * @param sqlString
     * @return
     * @return int
     * @throws
     * @author feifei.liu
     * @date 2015年9月21日 上午9:44:53
     */
    public int execute(String sqlString){
        Query query=em.createNativeQuery(sqlString);
        return query.executeUpdate();
    }

    /**
     * @Description: 执行HQL更新语句
     * @param updateQuery
     * @return
     * @return int
     * @throws
     * @author feifei.liu
     * @date 2015年10月10日 下午12:day01:51
     */
    public int updateQuery(String updateQuery){
        Query query=em.createQuery(updateQuery);
        return query.executeUpdate();
    }

    /**
     *
     * @param example
     * @param pageable
     * @return
     * @author feifei.liu
     * @date 2017年4月14日 下午22:52:day01
     */
    public Page<T> findByAuto(T example, Pageable pageable) {
        return findAll(DynamicSpecifications.byAuto(em, example), pageable);
    }
}
