package com.library.scheduler.core.jpa.controller;

import com.library.scheduler.core.jpa.dao.PersonRepository;
import com.library.scheduler.core.jpa.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by liuff on 2017/4/12.
 */
@RestController
public class DataController {
    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/save")
    public Person save(String name, String address, Integer age) {
        Person p = personRepository.save(new Person(null, name, age, address));
        return p;
    }

    @RequestMapping("/q1")
    public List<Person> q1(String name) {
        System.out.println(personRepository.count());
        List<Person> people = personRepository.findByName(name);
        return people;
    }

    @RequestMapping("/auto")
    public Page<Person> auto(Person person) {
        Page<Person> pagePerple = personRepository.findByAuto(person, new PageRequest(0, 10));
        return pagePerple;
    }
}
