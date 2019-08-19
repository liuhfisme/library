package com.library.valid.model;

import com.library.valid.support.custom.MobilePhone;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * 后台参数验证Bo.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-day01-17
 */
@Data
public class ValidBo {
    /**
     * 非空校验
     */
    @NotNull
    @Max(100)
    private Integer id;

    /**
     * 字符长度校验
     */
    @Length(min = 10, max = 20)
    private String name;

    /**
     * 数组/集合子集个数验证
     */
    @Size(min = 1, max = 3)
    private String[] books;

    /**
     * 浮点性数值验证
     */
    @DecimalMax(value = "3.14")
    private BigDecimal decimalMax;

    @DecimalMin(value = "0.14")
    private BigDecimal decimalMin;

    @Email
    private String email;

    @MobilePhone
    private String phone;


}
