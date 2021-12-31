package com.cch.demojwt.exception;

import com.cch.demojwt.definition.ResponseCode;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomException extends RuntimeException {

    private ResponseCode code;

    public CustomException(ResponseCode code) {
        this.code = code;
    }

    public CustomException(Throwable cause, ResponseCode code) {
        super(cause);
        this.code = code;
    }
}
