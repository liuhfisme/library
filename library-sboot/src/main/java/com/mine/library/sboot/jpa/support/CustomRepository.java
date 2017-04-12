package com.mine.library.sboot.jpa.support;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import java.io.Serializable;
import java.util.List;

/**
 * Created by liuff on 2017/4/13.
 */
@NoRepositoryBean
public interface CustomRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    public Object saveObject(Object entity);

    public T findOne(Specification<T> spec);

    public Object findOne(Class<?> entityClass, Object primaryKey);

    public List<T> findAll(Specification<T> spec);

    public long count(Specification<T> spec);

    @SuppressWarnings("rawtypes")
    public List findAll(String queryString);

    @SuppressWarnings("rawtypes")
    public List findBySql(String sqlString);

//    public Map<QueryType, Object> findBySql(String sqlString, int start, int length);

    @SuppressWarnings("rawtypes")
    public List findBySql(String sqlString, Class resultClass);

    @SuppressWarnings("rawtypes")
    public List findBySql(String sqlString,String resultSetMapping);

    public void clear();

    public void rollback();

    public Query createNativeQuery(String sqlString);

    public int execute(String sqlString);

    public int updateQuery(String updateQuery);

    public StoredProcedureQuery createStoredProcedureQuery(String procedureName);
}
