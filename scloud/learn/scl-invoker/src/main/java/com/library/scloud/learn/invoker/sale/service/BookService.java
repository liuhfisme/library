package com.library.scloud.learn.invoker.sale.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 图书服务客户端.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-02-21
 */
@FeignClient("provider-service")
public interface BookService {
    /**
     * 选择图书商品
     * @param bookId
     * @return Object
     */
    @RequestMapping(value = "/book/{bookId}")
    Object choose(@PathVariable Integer bookId);
}