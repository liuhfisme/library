package com.library.valid.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * APP 授权申请工具类.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-03-06
 */
@Data
@AllArgsConstructor
public class AppAuth {
    private String app_key;
    private String app_secret;
}