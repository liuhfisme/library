package com.eureka.provider.book.controller;

import com.eureka.provider.book.model.Book;
import com.eureka.provider.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 图书控制器.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-02-21
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/{bookId}")
    public Book choose(@PathVariable Integer bookId) {
        return bookService.choose(bookId);
    }
}