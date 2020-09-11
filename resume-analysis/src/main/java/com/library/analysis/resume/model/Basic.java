package com.library.analysis.resume.model;

import lombok.Data;

/**
 * 基本信息.
 *  姓名、性别、生日、住址、手机号码、邮箱
 *
 * @date 2020-09-10
 * @version 1.0
 * @author liufefei02@beyondsoft.com
 */
@Data
public class Basic {
    private String name;
    private String sex;
    private String birthday;
    private String address;
    private String phoneNum;
    private String email;
}