package com.library.scheduler.core.jpa.dao;

import com.library.scheduler.core.jpa.domain.Person;
import com.library.scheduler.core.jpa.support.CustomRepository;

import java.util.List;

/**
 * Created by liuff on 2017/4/12.
 */
public interface PersonRepository extends CustomRepository<Person, Long> {
    List<Person> findByName(String name);
    Person findByNameAndAddress(String name, String address);
}
