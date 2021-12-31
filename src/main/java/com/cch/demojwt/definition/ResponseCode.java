package com.cch.demojwt.definition;

public enum ResponseCode {
    SUCCESS(200, "success"),
    FORBIDDEN(403, "Access denied"),
    RESOURCES_NOT_EXIST (404, "Resource does not exist"),
    SERVICE_ERROR (500, "Server Exception"),
    UNPROCESSABLE_ENTITY (422, "Unprocessable Entity"),
    ACCOUNT_IS_EXIST (423, "Account is exist"),
    ROLE_NOT_EXIST (424, "Role not exist"),
    EMAIL_IS_EXIST (425, "Email is exist");

    private Integer code;

    private String msg;

    private ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
