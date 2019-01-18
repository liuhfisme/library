package com.library.util;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * easypoi.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-01-18
 */
@Data
@AllArgsConstructor
public class Person {
    @Excel(name = "姓名", orderNum = "0")
    private String name;
    @Excel(name = "性别", replace = {"男_1", "女_2"}, orderNum = "1")
    private String sex;
    @Excel(name = "生日", exportFormat = "yyyy-MM-dd", orderNum = "2")
    private Date birthday;
}