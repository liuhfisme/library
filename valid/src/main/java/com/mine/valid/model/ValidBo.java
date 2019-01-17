package com.mine.valid.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 后台参数验证Bo.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-01-17
 */
@Data
public class ValidBo {
    @NotNull(message = "is null")
    private Long id;
    @NotNull(message = "is null")
    private String name;
}