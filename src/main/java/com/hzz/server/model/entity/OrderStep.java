package com.hzz.server.model.entity;

import lombok.Data;

import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 订单操作记录
 * 才华有限公司 @author Administrator
 *
 * @create 2018/11/6
 **/
@Data
@Table(name="hotel_order_step")
public class OrderStep {

    private Long id;
    private String orderCode;
    private LocalDateTime stepTime;
    private Integer preState;
    private Integer sufState;
    private String stepTitle;
    private String stepDesc;
    private String operateUser;

}
