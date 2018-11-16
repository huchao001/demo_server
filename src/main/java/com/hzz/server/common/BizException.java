package com.hzz.server.common;


import com.hzz.server.util.ErrCodeEnum;

public class BizException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    //请求太频繁
    public static final String REQ_TOO_TIMES = "1000";
    //不存在记录
    public static final String RECORD_EXIST = "1002";

    private String errorCode = "-1";
    private String logInfo;
    private Object data;
    

    public BizException() {
        super();
    }

    public BizException(ErrCodeEnum errCodeEnum) {
        super(errCodeEnum.getMsg());
        this.errorCode = errCodeEnum.getCode();
    }
    public BizException(ErrCodeEnum errCodeEnum, Object data) {
    	super(errCodeEnum.getMsg());
    	this.errorCode = errCodeEnum.getCode();
    	this.data = data;
    }
    public BizException(String errorCode, String message, Object data) {
    	super(message);
    	this.errorCode = errorCode;
    	this.data = data;
    }

    public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BizException(String errorCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Object data) {
        super(message);
        this.data = data;
    }

    public BizException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
