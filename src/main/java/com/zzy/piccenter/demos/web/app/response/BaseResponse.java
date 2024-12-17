package com.zzy.piccenter.demos.web.app.response;

import com.zzy.piccenter.demos.web.infrastructure.common.exception.ErrorCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @author T041018
 */
@Data
public class BaseResponse<T> implements Serializable {

    private int code;

    private T data;

    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }
}

