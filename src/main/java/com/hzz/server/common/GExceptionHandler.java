package com.hzz.server.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.HandlerMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class GExceptionHandler {
    private static Logger Log = LoggerFactory.getLogger(GExceptionHandler.class);

    @ExceptionHandler(value = BizException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object exception(final BizException ex, final ServletWebRequest req, final HandlerMethod handlerMethod) {
        Log.error("业务异常:" + req + ex.getErrorCode(), ex);
        Map<String, Object> view = new HashMap<>(2);
        view.put("code", ex.getErrorCode());
        if (!StringUtils.isEmpty(ex.getLocalizedMessage())) {
            view.put("message", ex.getLocalizedMessage());
        } else {
            view.put("message", ex.getMessage());
        }
        return view;
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object exception(final BindException ex, final ServletWebRequest req, final HandlerMethod handlerMethod) {
        Log.error("参数异常:" + ex.getMessage(), ex);
        BindingResult r = ex.getBindingResult();
        Map<String, Object> view = new HashMap<>(2);
        view.put("code", "2");
        view.put("message", r.getFieldErrors().get(0).getDefaultMessage()); //默认显示第一个提示信息
        return view;
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object exception(final MethodArgumentNotValidException ex, final ServletWebRequest req, final HandlerMethod handlerMethod) {
        Log.error("请求数据不合法:" + ex.getMessage(), ex);
        BindingResult r = ex.getBindingResult();
        Map<String, Object> view = new HashMap<>(2);
        view.put("code", "2");
        view.put("message", r.getFieldErrors().get(0).getDefaultMessage()); //默认显示第一个提示信息
        return view;
    }


    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public Object exception(final HttpRequestMethodNotSupportedException ex, final ServletWebRequest req, final HandlerMethod handlerMethod) {
        Log.error("请求方法异常:" + ex.getMessage(), ex);
        Map<String, Object> view = new HashMap<>(2);
        view.put("code", "2");
        if (!Objects.isNull(ex.getMessage()) && !"".equals(ex.getMessage())) {
            view.put("message", ex.getMessage());//默认显示第一个提示信息
            return view;
        }
        view.put("message", "请求方法不支持");//默认显示第一个提示信息
        return view;
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleRunTimeExceptions(final IllegalArgumentException ex, final ServletWebRequest req, final HandlerMethod handlerMethod) {
        Log.error("系统异常,参数不合法:" + req, ex);
        Map<String, Object> view = new HashMap<>(2);
        view.put("code", 2);
        view.put("message", "系统繁忙,请联系管理员!");
        return view;
    }

    /**
     * 全局处理Exception 错误的情况下返回500
     *
     * @param ex
     * @param req
     * @return
     */
    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleOtherExceptions(final Exception ex, final ServletWebRequest req) {
        Log.error("系统异常:" + req, ex);
        Map<String, Object> view = new HashMap<>(2);
        view.put("code", 2);
        view.put("message", "系统繁忙,请稍后再试!");
        return view;
    }

}
