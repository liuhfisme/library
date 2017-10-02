package com.library.library.sboot.jpa.support;

import com.library.library.sboot.jpa.specs.CustomSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import java.io.Serializable;
import java.util.List;

/**
 * Created by liuff on 2017/4/13.
 */
public class CustomRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements CustomRepository<T, ID> {
    private final EntityManager em;

    public CustomRepositoryImpl(JpaEntityInformation entityInformation, EntityManager em) {
        super(entityInformation, em);
        this.em = em;
    }

//    public CustomRepositoryImpl(Class<T> domainClass, EntityManager em) {
//        super(domainClass, em);
//        this.em = em;
//    }

    /* (non-Javadoc)
     * <p>Title: rollback</p>
     * <p>Description: 事务回滚</p>
     * @see com.baihui.core.repository.BaseRepository#rollback()
     */
    @Override
    public void rollback(){
        em.getTransaction().rollback();
    }

    /* (non-Javadoc)
    * <p>Title: saveObject</p>
    * <p>Description: 数据保存</p>
    * @param Object
    * @return
    * @see com.baihui.core.repository.BaseRepository#saveObject(java.lang.Object)
    */
    @Override
    public Object saveObject(Object entity) {
        return  em.merge(entity);
    }

    /**
     * @Description: 查询：根据对象类型和主键
     * @param entityClass
     * @param primaryKey
     * @return
     * @return Object
     * @throws
     * @author feifei.liu
     * @date 2016年8月14日 下午4:06:56
     */
    public Object findOne(Class<?> entityClass, Object primaryKey) {
        return em.find(entityClass, primaryKey);
    }

    /* (non-Javadoc)
    * <p>Title: findAll</p>
    * <p>Description: 根据HQL语句获取查询结果集合</p>
    * @param queryString
    * @return
    * @see com.baihui.core.repository.BaseRepository#findAll(java.lang.String)
    */
    @SuppressWarnings({ "rawtypes" })
    @Override
    public List findAll(String queryString) {
        return em.createQuery(queryString).getResultList();
    }
    /* (non-Javadoc)
    * <p>Title: findBySql</p>
    * <p>Description: 根据SQL语句获取查询结果集合</p>
    * @param sqlString
    * @return
    * @see com.baihui.core.repository.BaseRepository#findBySql(java.lang.String)
    */
    @SuppressWarnings("rawtypes")
    @Override
    public List findBySql(String sqlString) {
        System.out.println(sqlString+"-");
        Query query=em.createNativeQuery(sqlString);
        return query.getResultList();
    }


    /* (non-Javadoc)
    * <p>Title: findBySql</p>
    * <p>Description: 根据SQL语句、实体类获取查询结果集合</p>
    * @param sqlString
    * @param resultClass
    * @return
    * @see com.baihui.core.repository.BaseRepository#findBySql(java.lang.String, java.lang.Class)
    */
    @SuppressWarnings("rawtypes")
    @Override
    public List findBySql(String sqlString, Class resultClass){
        Query query=em.createNativeQuery(sqlString, resultClass);
        return query.getResultList();
    }

    /* (non-Javadoc)
    * <p>Title: findBySql</p>
    * <p>Description:  根据SQL语句、resultSetMapping获取查询结果集合</p>
    * @param sqlString
    * @param resultSetMapping
    * @return
    * @see com.baihui.core.repository.BaseRepository#findBySql(java.lang.String, java.lang.String)
    */
    @SuppressWarnings("rawtypes")
    @Override
    public List findBySql(String sqlString,String resultSetMapping){
        Query query=em.createNativeQuery(sqlString, resultSetMapping);
        return query.getResultList();
    }

    /* (non-Javadoc)
    * <p>Title: clear</p>
    * <p>Description: </p>
    * @see com.baihui.core.repository.BaseRepository#clear()
    */
    @Override
    public void clear() {
        em.clear();
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
    /**
     * @Description: 执行SQL语句
     * @param sqlString
     * @return
     * @return int
     * @throws
     * @author feifei.liu
     * @date 2015年9月21日 上午9:44:53
     */
    @Override
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
     * @date 2015年10月10日 下午12:01:51
     */
    @Override
    public int updateQuery(String updateQuery){
        Query query=em.createQuery(updateQuery);
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
    /**
     * ClassName: QueryType
     * @Description: 查询类型枚举
     * @author feifei.liu
     * @date 2015年9月21日 上午10:06:37
     */
    public enum QueryType {
        total, data
    }

    /**
     *
     * @param example
     * @param pageable
     * @return
     * @author feifei.liu
     * @date 2017年4月14日 下午22:52:01
     */
    @Override
    public Page<T> findByAuto(T example, Pageable pageable) {
        return findAll(CustomSpecification.byAuto(em, example), pageable);
    }
}
