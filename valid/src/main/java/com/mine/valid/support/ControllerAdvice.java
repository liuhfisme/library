package com.mine.valid.support;

import com.mine.valid.model.ResponseData;
import com.mine.valid.model.ValidVo;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * 异常拦截.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-01-17
 */
@RestControllerAdvice
@RestController
public class ControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    public ResponseData exception(Exception exception) {
        if (exception instanceof MethodArgumentNotValidException) {
            System.out.println(exception.getMessage());
            MethodArgumentNotValidException validException = (MethodArgumentNotValidException) exception;
            BindingResult bindingResult = validException.getBindingResult();
            List<FieldError> errors = bindingResult.getFieldErrors();
            List<ValidVo> validVos = new ArrayList<>();
            errors.forEach(error ->
                validVos.add(new ValidVo(error.getField(), error.getDefaultMessage()))
            );
            return ResponseData.instance(HttpStatus.BAD_REQUEST.value(), "4001", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), validVos);
        }
        return ResponseData.instance(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), exception.getMessage());
    }
}