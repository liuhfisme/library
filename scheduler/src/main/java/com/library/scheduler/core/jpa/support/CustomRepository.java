package com.library.scheduler.core.jpa.support;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    Object saveObject(Object entity);

    T findOne(Specification<T> spec);

    Object findOne(Class<?> entityClass, Object primaryKey);

    List<T> findAll(Specification<T> spec);

    long count(Specification<T> spec);

    @SuppressWarnings("rawtypes")
    List findAll(String queryString);

    @SuppressWarnings("rawtypes")
    List findBySql(String sqlString);

//    public Map<QueryType, Object> findBySql(String sqlString, int start, int length);

    @SuppressWarnings("rawtypes")
    List findBySql(String sqlString, Class resultClass);

    @SuppressWarnings("rawtypes")
    List findBySql(String sqlString, String resultSetMapping);

    void clear();

    void rollback();

    Query createNativeQuery(String sqlString);

    int execute(String sqlString);

    int updateQuery(String updateQuery);

    StoredProcedureQuery createStoredProcedureQuery(String procedureName);

    Page<T> findByAuto(T example, Pageable pageable);
}
