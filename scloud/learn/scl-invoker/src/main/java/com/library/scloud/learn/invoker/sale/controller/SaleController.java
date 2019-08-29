package com.library.scloud.learn.invoker.sale.controller;

import com.library.scloud.learn.invoker.HelloFeignClient;
import com.library.scloud.learn.invoker.sale.service.BookService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class SaleController {
    @Autowired
    private BookService bookService;

    @Autowired
    private HelloFeignClient helloFeignClient;

    @RequestMapping(value = "/book/{bookId}")
    public Object sale(@PathVariable Integer bookId) {
        log.debug("hello: ", helloFeignClient.hello("feifei.liu"));
        return bookService.choose(bookId);
    }

}
