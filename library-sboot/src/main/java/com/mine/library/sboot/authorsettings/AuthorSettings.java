package com.mine.library.sboot.authorsettings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by liuff on 2017/4/5.
 */
@Component
@ConfigurationProperties(prefix = "author")
public class AuthorSettings {
    private String name;
    private Long age;

    public AuthorSettings(){
        super();
    }
    public AuthorSettings(String name, Long age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}
