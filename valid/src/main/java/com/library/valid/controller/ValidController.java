package com.library.valid.controller;

import com.library.valid.model.ResponseData;
import com.library.valid.model.ValidBo;
import org.springframework.http.HttpStatus;
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
    public ResponseData valid(@RequestBody @Validated ValidBo validBo) {
        if (true) {
            throw new IllegalArgumentException("123");
        }
        return ResponseData.instance(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "SUCCESS");
    }
}