package com.library.utils.poi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 动态字段封装类.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-03-12
 */
@Data
@AllArgsConstructor
public class DynamicField {
    private String name;
    private String showName;
    private String showType;
}