package com.hzz.server.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

/**
 * 酒店其他相关信息
 * 才华有限公司 @author Administrator
 *
 * @create 2018/10/16
 **/
@Data
@Table(name = "hotel_other")
public class HotelOther {

    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    private Long hotelId;   // 酒店id
    private Integer typeCode;   // '酒店其他信息 1-酒店设施 2-酒店政策 3-酒店周边'
    private Integer typeName;   // 类别名称
    private Integer typeContent;   // 具体内容（多条信息，用“|”分割）

}
