package com.mine.valid.support.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * 自定义验证- 手机号.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-01-24
 */
public class MobilePhoneValidator implements ConstraintValidator<MobilePhone, CharSequence> {
    private static final String REGEX = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    @Override
    public void initialize(MobilePhone constraintAnnotation) {

    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.nonNull(value) && value.length() > 0 &&
            !Pattern.matches(REGEX, value)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(Pattern.matches(REGEX, "15511978870"));
    }
}