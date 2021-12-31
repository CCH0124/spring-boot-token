package com.cch.demojwt.response.VO;

import java.io.Serializable;
import com.cch.demojwt.definition.ResponseCode;

public class ResponseResult<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public ResponseResult(Integer code, String msg , T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }   
    
    public ResponseResult() {
        
    }

    public static ResponseResult<Void> success() {
        return new ResponseResult<Void>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg() , null);
    }

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<T>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    
}
