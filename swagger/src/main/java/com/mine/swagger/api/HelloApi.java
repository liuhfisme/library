package com.mine.swagger.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;

/**
 * 测试API文档.
 *
 * @author liufeifei02@beyondsoft.com
 * @version 1.0
 * @date 2019-07-26
 */
@Api(value = "Hello World", description = "the hello api")
public interface HelloApi {
    @ApiOperation(value = "/hello", notes = "hello request")
    @ApiParam(name = "姓名", value = "name", defaultValue = "张三", required = true)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok", response = String.class, examples = @Example(@ExampleProperty(value = "data")))
    })
    String hello(String name);
}
