package com.library.scloud.learn.invoker.sale.controller;

import com.library.scloud.learn.invoker.sale.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 销售控制器.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-02-21
 */
@RestController
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/book/{bookId}")
    public Object sale(@PathVariable Integer bookId) {
        return bookService.choose(bookId);
    }
}