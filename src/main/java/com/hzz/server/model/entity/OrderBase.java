package com.hzz.server.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 订单信息
 * 才华有限公司 @author Administrator
 *
 * @create 2018/11/6
 **/
@Data
@Table(name = "hotel_order")
public class OrderBase {

    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    private String kissoid;
    private String crfUid;
    private String orderCode;

    private Integer orderStatus;
    // 0 试预订 100 预定成功 200 本地支付成功 300 通知TTY支付成功
    // 支付前（取消订单处理中）：101 买家取消订单 102 运营取消订单 103 系统取消订单
    // 支付前（取消订单成功）：1001 买家取消订单 1002 运营取消订单 1003 系统取消订单（比如超时）

    // 支付后：201 买家申请退款 202 买家取消退款申请
    // 2001 运营同意退款 2002 运营取消订单并退款 2003 运营不同意退款

    //

    private Integer thirdPChannel;    // 第三方渠道，0-泰坦云
    private String thirdPOrderCode;   // 第三方订单号

    private String ratePlanId;        // 价格计划id
    private LocalDate checkInDate;    // 入住日期
    private LocalDate checkOutDate;   // 离店日期
    private Integer roomNum;          // 预定房间数
    private Integer bedType;          // 床型
    private Long totalAmount;         // 订单金额（分）
    private String linkMan;           // 订单联系人
    private String linkPhone;         // 联系人电话
    private String email;             // 邮箱（非必填）
    private String arriveTime;        // 到店时间（HH:mm）
    private String latestArriveTime;  // 最晚到店时间（HH:mm）
    private String supplyCode;        // 供应商编码
    private String remark;            // 备注信息
    private String specialDemand;     // 填写客人对酒店的特殊要求 （非必填）

    private String priceItemJson;     // 订单每日价格详情列表 （json格式）
    private String guestInfoJson;     // 入住人列表信息 （json格式）

    private Integer orderType;        // 订单类型（11 酒店）
    private String xrfRemark;         // 运营备注信息
    private LocalDateTime expireTime; // 当前状态失效时间
    private LocalDateTime createTime;
    private LocalDateTime lastModifyTime;

}