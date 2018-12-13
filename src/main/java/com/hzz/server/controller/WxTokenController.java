package com.hzz.server.controller;

import io.swagger.annotations.Api;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Arrays;

@Api(value = "WxTokenController", tags = "WxTokenController", description = "微信TOKEN", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@RequestMapping("/wx")
public class WxTokenController {
    public static final String TOKEN = "hzz.token";
    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/signature")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        LOGGER.info("check token，{}", request.getParameterNames());

        try {
            // 开发者提交信息后，微信服务器将发送GET请求到填写的服务器地址URL上，GET请求携带参数
            String signature = request.getParameter("signature");// 微信加密签名（token、timestamp、nonce。）
            String timestamp = request.getParameter("timestamp");// 时间戳
            String nonce = request.getParameter("nonce");// 随机数
            String echostr = request.getParameter("echostr");// 随机字符串

            LOGGER.info("signature={},timestamp={},nonce={},echostr={}", signature, timestamp, nonce, echostr);

            PrintWriter out = response.getWriter();
            // 将token、timestamp、nonce三个参数进行字典序排序
            String[] params = new String[]{TOKEN, timestamp, nonce};
            Arrays.sort(params);
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            String clearText = params[0] + params[1] + params[2];
            String algorithm = "SHA-1";
            String sign = new String(
                    Hex.encodeHex(MessageDigest.getInstance(algorithm).digest((clearText).getBytes()), true));
            // 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
            if (signature.equals(sign)) {
                response.getWriter().print(echostr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
