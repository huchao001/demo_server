package com.hzz.server.common;


import com.hzz.server.util.ErrCodeEnum;

public class AjaxResult {
    public static final String CODE_SUCCESS = "0";  // 0  表示成功
    public static final String CODE_FAILED = "-1";  // -1 表示未知原因的失败

    private String code;    // 0  表示成功 ,-1或非0 表示失败
    private Object data;
    private String message;

    private AjaxResult(String code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public String getCode() {
        return code;
    }

    public static final AjaxResult success() {
        return new AjaxResult(CODE_SUCCESS, null, "ok");
    }

    public static final AjaxResult success(Object data) {
        return new AjaxResult(CODE_SUCCESS, data, "ok");
    }

    public static final AjaxResult success(String message) {
        return new AjaxResult(CODE_SUCCESS, null, message);
    }

    public static final AjaxResult success(Object data, String message) {
        return new AjaxResult(CODE_SUCCESS, data, message);
    }

    public static final AjaxResult failed() {
        return new AjaxResult(CODE_FAILED, null, "fail");
    }

    public static final AjaxResult failed(Object data) {
        return new AjaxResult(CODE_FAILED, data, "fail");
    }

    public static final AjaxResult failed(String message) {
        return new AjaxResult(CODE_FAILED, null, message);
    }

    public static final AjaxResult failed(Object data, String message) {
        return new AjaxResult(CODE_FAILED, data, message);
    }

    public static final AjaxResult failed(ErrCodeEnum err) {
        return new AjaxResult(err.getCode(), "", err.getMsg());
    }

    public static final AjaxResult failed(ErrCodeEnum err, Object data) {
        return new AjaxResult(err.getCode(), data, err.getMsg());
    }

}
