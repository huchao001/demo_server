package com.hzz.server.util;


import com.hzz.server.common.BizException;

/**
 * 订单状态枚举
 * 才华有限公司 @author Administrator
 *
 * @create 2018/11/6
 **/
public enum OrderStateEnum {

    // 0 试预订 100 预定成功 200 本地支付成功 300 通知TTY支付成功
    // 支付前（取消订单处理中）：101 买家取消订单 102 运营取消订单 103 系统取消订单
    // 支付前（取消订单成功）：1001 买家取消订单 1002 运营取消订单 1003 系统取消订单（比如超时）

    // 支付后：201 买家申请退款 202 买家取消退款申请
    // 2001 运营同意退款 2002 运营取消订单并退款 2003 运营不同意退款

    试预订(0, ""),
    预订成功(100, ""),
    支付成功(200, ""),
    通知TTY成功(300, ""),

    // 支付前取消订单
    客户取消订单(101, ""),
    运营取消订单(102, ""),
    系统取消订单(103, ""),

    //
    客户取消订单完成(1001, ""),
    运营取消订单完成(1002, ""),
    系统取消订单完成(1003, ""),

    // 支付后
    买家申请退款(201, ""),
    买家取消退款申请(202, ""),

    退款成功(2001, ""),
    退款完成(2001, "退款成功并且通知tty取消订单成功"),;

    public int state;
    public String title;

    OrderStateEnum(int state, String title) {
        this.state = state;
        this.title = title;
    }

    public static OrderStateEnum getByState(int state) {
        for (OrderStateEnum obj : OrderStateEnum.values()) {
            if (state == obj.state) {
                return obj;
            }
        }
        throw new BizException("非法的订单状态");
    }
}
