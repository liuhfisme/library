package com.library.analysis.resume.model;

import lombok.Data;

/**
 * 求职意向.
 *  工作性质、期望工作地点、期望职位、期望薪资
 * @date 2020-09-10
 * @version 1.0
 * @author liufefei02@beyondsoft.com
 */
@Data
public class Intention {
    private String nature;
    private String address;
    private String position;
    private String salary;
}