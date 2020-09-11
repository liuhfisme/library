package com.library.analysis.resume.model;

import lombok.Data;

import java.util.List;

/**
 * 通用类别.
 *
 * @date 2020-09-10
 * @version 1.0
 * @author liufefei02@beyondsoft.com
 */
@Data
public class Classification {
    private List<String> basics;
    private List<String> intentions;
    private List<String> evaluations;
    private List<String> educations;
    private List<String> works;
    private List<String> others;
    private List<String> skills;
    private List<String> pms;
}