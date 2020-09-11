package com.library.analysis.resume.model;

import lombok.Data;

import java.util.List;

/**
 * 简历.
 *
 * @date 2020-09-10
 * @version 1.0
 * @author liufefei02@beyondsoft.com
 */
@Data
public class Resume {
    private Basic basic;
    private Intention intention;
    private Evaluation evaluation;
    private EducationExp educationExp;
    private WorkExp workExp;
    private List<Other> others;
}