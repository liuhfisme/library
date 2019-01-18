package com.mine.valid.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 参数校验返回VO.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-01-18
 */
@Data
@AllArgsConstructor
public class ValidVo {
    private String fieldName;
    private String failureMsg;
}