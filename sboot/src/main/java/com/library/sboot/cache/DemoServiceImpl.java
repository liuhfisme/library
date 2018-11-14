package com.library.sboot.cache;

import com.library.sboot.jpa.dao.PersonRepository;
import com.library.sboot.jpa.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by liuff on 2017/5/11.
 */
@Service
public class DemoServiceImpl implements DemoService{
    @Autowired
    PersonRepository personRepository;


    @Override
    @CachePut(value = "people", key = "#person.id")
    public Person save(Person person) {
        Person p = personRepository.save(person);
        System.out.println("为id、key为："+p.getId()+"数据做了缓存");
        return p;
    }

    @Override
    @CacheEvict(value = "people")
    public void remove(Long id) {
        System.out.println("删除了id、key为"+id+"的数据缓存");
        //personRepository.delete(id);
    }

    @Override
    @Cacheable(value = "people", key = "#person.id")
    public Person findOne(Person person) {
        Person p = personRepository.findOne(person.getId());
        System.out.println("为id、key为："+p.getId()+"数据做了缓存");
        return p;
    }
}
