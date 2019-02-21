package com.eureka.provider;

/**
 * Created by Administrator on 2018/10/17.
 */
public class HelloWorld {
    private String id;
    private String name;
    private Integer age;
    private String message;

    public HelloWorld() {
    }

    public HelloWorld(String id, String name, Integer age, String message) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
