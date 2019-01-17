package com.mine.valid.controller;

import com.mine.valid.model.ValidBo;
import com.mine.valid.model.ValidVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台参数验证控制器.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-01-17
 */
@RestController
@RequestMapping("/valid")
public class ValidController {

    @RequestMapping
    public ValidVo valid(@RequestBody @Validated ValidBo validBo) {
        if (true) {
            throw new IllegalArgumentException("123");
        }
        return new ValidVo("x", "y");
    }
}