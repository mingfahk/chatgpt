package com.mingfahk.chatgpt.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * 全局返回对象
 *
 * @param <T>
 */
@Data
@Accessors(chain = true)
public class BaseResponse<T> {
    private Boolean flag;
    private String code;
    private String msg;
    private T body;


    public BaseResponse<T> success() {
        return build(Status.SUCCESS);
    }

    public BaseResponse<T> success(T body) {
        return build(Status.SUCCESS).setFlag(true);
    }

    public BaseResponse<T> fail() {
        return build(Status.FAIL).setFlag(false);
    }

    public BaseResponse<T> fail(T body) {
        return build(Status.FAIL,body).setFlag(false);
    }


    public BaseResponse<T> build(Status status) {
        return new BaseResponse<T>().setCode(status.getCode()).setMsg(status.getMsg());
    }

    public BaseResponse<T> build(Status status, T body) {
        return new BaseResponse<T>().setCode(status.getCode()).setMsg(status.getMsg()).setBody(body);
    }


    @Getter
    @AllArgsConstructor
    enum Status {
        SUCCESS("0", "success"),
        FAIL("-1", "API call exception");

        private String code;
        private String msg;
    }
}
