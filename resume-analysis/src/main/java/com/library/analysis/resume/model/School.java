package com.library.analysis.resume.model;

import lombok.Data;

/**
 * 教育经历详细.
 *  时间、学校、专业、学历
 * @date 2020-09-10
 * @version 1.0
 * @author liufefei02@beyondsoft.com
 */
@Data
public class School {
    private String name;
    private String time;
    private String major;
    private String background;
}