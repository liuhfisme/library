package com.library.analysis.resume.model;

import lombok.Data;

import java.util.List;

/**
 * 工作经历.
 *
 * @date 2020-09-10
 * @version 1.0
 * @author liufefei02@beyondsoft.com
 */
@Data
public class WorkExp {
    private List<Business> businesses;
}