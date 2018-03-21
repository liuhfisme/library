package com.mine.library.sboot.jpa.dao;

import com.mine.library.sboot.jpa.domain.Person;
import com.mine.library.sboot.jpa.support.CustomRepository;

import java.util.List;

/**
 * Created by liuff on 2017/4/12.
 */
public interface PersonRepository extends CustomRepository<Person, Long> {
    List<Person> findByName(String name);
    Person findByNameAndAddress(String name, String address);
}
