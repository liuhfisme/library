package com.mine.valid.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 参数验证Vo.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-01-17
 */
@Data
@AllArgsConstructor
public class ValidVo {
    private String code;
    private String msg;
}