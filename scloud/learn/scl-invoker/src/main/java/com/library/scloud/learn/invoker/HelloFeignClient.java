package com.library.scloud.learn.invoker;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Feign 测试.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-02-21
 */
@FeignClient("provider-service")
public interface HelloFeignClient {
    @RequestMapping(method = RequestMethod.GET, value ="/hello/{name}")
    Object hello(@PathVariable("name") String name);
}
