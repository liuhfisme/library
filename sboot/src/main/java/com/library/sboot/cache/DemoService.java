package com.library.sboot.cache;

import com.library.sboot.jpa.domain.Person;

/**
 * Created by liuff on 2017/5/11.
 */
public interface DemoService {
    public Person save(Person person);
    public void remove(Long id);
    public Person findOne(Person person);
}
