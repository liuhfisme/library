package com.mine.valid.model;

import com.mine.valid.constant.ValidConstant;
import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 后台参数验证Bo.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-01-17
 */
@Data
public class ValidBo {
    @NotNull
    private Long id;
    @Max(value = 10)
    private String name;
    @DecimalMax(value = "3.14")
    private BigDecimal decimalMax;
    @DecimalMin(value = "0.14")
    private BigDecimal decimalMin;
    @Email
    private String email;


}