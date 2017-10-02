package com.library.library.sboot.jpa.dao;

import com.library.library.sboot.jpa.domain.Person;
import com.library.library.sboot.jpa.support.CustomRepository;

import java.util.List;

/**
 * Created by liuff on 2017/4/12.
 */
public interface PersonRepository extends CustomRepository<Person, Long> {
    List<Person> findByName(String name);
    Person findByNameAndAddress(String name, String address);
}
