package com.mingfahk.chatgpt.advice;

import com.mingfahk.chatgpt.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler<T> {


    @ExceptionHandler(value = Exception.class)
    public BaseResponse<String> exceptionHandler(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return new BaseResponse<String>().fail(e.getMessage());
    }
}
