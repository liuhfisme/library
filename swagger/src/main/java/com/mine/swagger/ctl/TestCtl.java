package com.mine.swagger.ctl;

import com.mine.swagger.model.TestBo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-04-23
 */
@RestController
@RequestMapping
@Api(value = "测试接口Controller", tags = "测试1")
public class TestCtl {

    @ApiOperation(value = "测试用接口", notes = "测试用接口", httpMethod = "POST")
    @ApiImplicitParam(name = "testBo", value = "测试BO实体", dataType = "TestBo", required = true)
    @RequestMapping("/hello")
    public String hello(@RequestBody TestBo testBo) {
        return String.format("hello %s", testBo.getName());
    }
}