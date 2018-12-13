package com.hzz.server.controller;

import com.hzz.server.model.wx.TextMessage;
import com.hzz.server.util.MessageUtil;
import io.swagger.annotations.Api;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

@Api(value = "WxMessageController", tags = "WxMessageController", description = "微信Message", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@RequestMapping("/wx")
public class WxMessageController {
    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * 接受文本信息
     * 查询官方wiki 开头强调： 假如服务器无法保证在五秒内处理回复，则必须回复“success”或者“”（空串），否则微信后台会发起三次重试。
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/message")
    protected void accept(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("accept message，{}", request.getParameterNames());

        Map<String, String> message = MessageUtil.parseXml(request);
        String messageType = message.get("MsgType");
        String res_content = "未知";

        //接收的是文本消息
        if (MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(messageType)) {

            //打印接收所有参数
            LOGGER.info("message={}", ToStringBuilder.reflectionToString(message, ToStringStyle.MULTI_LINE_STYLE));

            String req_content = message.get("Content");


            if ("你好".equals(req_content)) {
                res_content = "你好啊!";
            } else if ("大家好".equals(req_content)) {
                res_content = "大家好啊!";
            } else if ("同志们好".equals(req_content)) {
                res_content = "为人民服务!";
            } else {
                //否则原样输出输入内容
                res_content = req_content;
            }
        }

        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(message.get("FromUserName"));   // 这里的ToUserName  是刚才接收xml中的FromUserName
        textMessage.setFromUserName(message.get("ToUserName"));   // 这里的FromUserName 是刚才接收xml中的ToUserName  这里一定要注意，否则会出错
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setMsgType(messageType);
        textMessage.setContent(res_content);

        String responseXml = MessageUtil.textMessageToXml(textMessage);

        PrintWriter out = response.getWriter();
        out.print(responseXml);
        out.close();

    }
}


// 参数	描述
// ToUserName	开发者微信号
// FromUserName	发送方帐号（一个OpenID）
// CreateTime	消息创建时间 （整型）
// MsgType	text
// Content	文本消息内容
// MsgId	消息id，64位整型

/*
接受文本消息
<xml>
<ToUserName><![CDATA[公众号]]></ToUserName>
<FromUserName><![CDATA[粉丝号]]></FromUserName>
<CreateTime>1460537339</CreateTime>
<MsgType><![CDATA[text]]></MsgType>
<Content><![CDATA[欢迎开启公众号开发者模式]]></Content>
<MsgId>6272960105994287618</MsgId>
</xml>

被动回复文本消息
<xml>
<ToUserName><![CDATA[粉丝号]]></ToUserName>
<FromUserName><![CDATA[公众号]]></FromUserName>
<CreateTime>1460541339</CreateTime>
<MsgType><![CDATA[text]]></MsgType>
<Content><![CDATA[test]]></Content>
</xml>*/
