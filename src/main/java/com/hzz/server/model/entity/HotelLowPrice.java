package com.hzz.server.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

/**
 * 酒店每日起价信息
 * 才华有限公司 @author Administrator
 *
 * @create 2018/10/16
 **/
@Data
@Table(name = "hotel_low_price")
public class HotelLowPrice {

    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    private Long hotelId;   // 酒店id

    private String saleDate;   // 日期（yyyy-MM-dd）
    private Double salePrice;  // 价格
}
