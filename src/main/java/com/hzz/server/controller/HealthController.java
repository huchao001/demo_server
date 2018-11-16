package com.hzz.server.controller;

import com.google.common.collect.ImmutableMap;
import com.hzz.server.common.AjaxResult;
import com.hzz.server.common.BizException;
import com.hzz.server.util.ErrCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/")
@ApiResponses({@ApiResponse(code = 200, message = "调用成功"), @ApiResponse(code = 400, message = "业务异常"),
        @ApiResponse(code = 401, message = "您没有登录"), @ApiResponse(code = 403, message = "权限不够,不允许访问"),
        @ApiResponse(code = 405, message = "请求的方法不支持"), @ApiResponse(code = 500, message = "系统内部错误"),
        @ApiResponse(code = 502, message = "网关超时"), @ApiResponse(code = 503, message = "服务不可达")})
@Api(value = "HealthController", tags = "HealthController", description = "服务健康状态监测", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class HealthController {
    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.profiles.swagger}")
    private String swagger;

    @Value("${spring.profiles}")
    private String profiles;

    RestTemplate restTemplate;

    @GetMapping(value = "status")
    @ResponseBody
    public String status() {
        LOGGER.info(swagger + "-" + profiles + "-");
        return "ok";
    }

    @GetMapping(value = "queryHotelIdList")
    @ResponseBody
    public String queryHotelIdList(HttpServletRequest request) {
        LOGGER.debug("...request", request);

        String str = "{\n" +
                "    \"retrunCode\":\"000\",\n" +
                "    \"retrunMsg\":\"成功\",\n" +
                "    \"bussinessResponse\":{\n" +
                "        \"hotelIds\":[\n" +
                "            179440,\n" +
                "            122164,\n" +
                "            131667\n" +
                "        ],\n" +
                "        \"totalPage\":1,\n" +
                "        \"currentPage\":1,\n" +
                "        \"total\":18\n" +
                "    }\n" +
                "}";

        return str;
    }

    @GetMapping(value = "log")
    @ResponseBody
    public String log() {

        LOGGER.debug("...debug");
        LOGGER.info("...info");
        LOGGER.warn("...warn");
        LOGGER.error("...error");

        boolean isDebug = LOGGER.isDebugEnabled();

        return isDebug + "";
    }

    @ApiOperation(value = "未知异常", notes = "未知异常")
    @GetMapping(value = "/f1")
    @ResponseBody
    public void f1() {
        throw new BizException("超时");
    }

    @ApiOperation(value = "已知异常", notes = "已知异常")
    @GetMapping(value = "/f2")
    @ResponseBody
    public void f2() {
        throw new BizException(ErrCodeEnum.err_server_01);
    }

    @ApiOperation(value = "失败", notes = "失败")
    @GetMapping(value = "/f3")
    @ResponseBody
    public AjaxResult f3() {
        Map data = ImmutableMap.of("a","1","b","2");
        return AjaxResult.failed(data);
    }

    @ApiOperation(value = "成功", notes = "成功")
    @GetMapping(value = "/s")
    @ResponseBody
    public AjaxResult s() {

        Map data = ImmutableMap.of("a","1","b","22");

        return AjaxResult.success(data);
    }

}
