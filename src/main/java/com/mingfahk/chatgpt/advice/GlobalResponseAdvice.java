package com.mingfahk.chatgpt.advice;

import com.mingfahk.chatgpt.common.BaseResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class GlobalResponseAdvice<T> implements ResponseBodyAdvice<T> {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return false;
    }

    @Override
    public T beforeBodyWrite(T body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body == null) {
            return (T) new BaseResponse<>().success();
        }

        if (body instanceof BaseResponse) {
            return body;
        }

        return (T) new BaseResponse<T>().success(body);

    }

}
