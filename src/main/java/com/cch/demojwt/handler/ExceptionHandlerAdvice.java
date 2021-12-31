package com.cch.demojwt.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.cch.demojwt.definition.ResponseCode;
import com.cch.demojwt.exception.CustomException;
import com.cch.demojwt.response.VO.ResponseResult;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {
    
    @ExceptionHandler(Exception.class)
    public ResponseResult<Void> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseResult<Void>(ResponseCode.SERVICE_ERROR.getCode(), ResponseCode.SERVICE_ERROR.getMsg(), null);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseResult<Void> handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return new ResponseResult<Void>(ResponseCode.SERVICE_ERROR.getCode(), ResponseCode.SERVICE_ERROR.getMsg(), null);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseResult<Void> handleBaseException(CustomException e) {
        log.error(e.getMessage(), e);
        ResponseCode code = e.getCode();
        return new ResponseResult<Void>(code.getCode(), code.getMsg(), null);
    }

    @ExceptionHandler(AccessDeniedException.class)
	public ResponseResult<Void> handleAccessDeniedException(HttpServletResponse res) throws IOException {
        ResponseCode code = ResponseCode.FORBIDDEN;
		return new ResponseResult<Void>(code.getCode(), code.getMsg(), null);
	}
}
