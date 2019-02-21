package com.eureka.provider.book.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 图书.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-02-21
 */
@Data
@AllArgsConstructor
public class Book {
    private Integer id;
    private String name;
    private String author;
}