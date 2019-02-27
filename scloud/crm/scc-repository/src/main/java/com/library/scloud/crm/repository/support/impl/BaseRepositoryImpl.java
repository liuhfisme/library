package com.library.scloud.crm.repository.support.impl;

import com.library.scloud.crm.repository.support.BaseRepository;
import com.library.scloud.crm.repository.support.BaseSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * 基础DAO实现.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-02-27
 */
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private final EntityManager em;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em;
    }

    @Override
    public Page<T> findByAuto(T example, Pageable pageable) {
        return findAll(BaseSpecification.byAuto(em, example), pageable);
    }
}