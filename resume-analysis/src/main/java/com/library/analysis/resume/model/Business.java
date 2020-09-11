package com.library.analysis.resume.model;

import lombok.Data;

/**
 * 工作经历.
 *  工作时间、工作名称、职位名称、工作内容
 * @date 2020-09-10
 * @version 1.0
 * @author liufefei02@beyondsoft.com
 */
@Data
public class Business {
    private String name;
    private String time;
    private String position;
    private String duty;
}