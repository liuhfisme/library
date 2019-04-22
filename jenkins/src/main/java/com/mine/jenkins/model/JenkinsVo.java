package com.mine.jenkins.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Jenkins自动构建Model.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-04-22
 */
@Data
@AllArgsConstructor
public class JenkinsVo {
    private String name;
    private Integer age;
    private String sex;
}