package com.library.analysis.resume.model;

import lombok.Data;

import java.util.List;

/**
 * 自我评价.
 *  专业技能、性格特点
 *
 * @date 2020-09-10
 * @version 1.0
 * @author liufefei02@beyondsoft.com
 */
@Data
public class Evaluation {
    private List<Skill> skills;
    private String personality;
}