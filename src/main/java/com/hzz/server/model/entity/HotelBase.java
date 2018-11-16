package com.hzz.server.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 酒店基本信息
 *
 * @create 2018/10/10
 **/
@Data
@Table(name = "hotel_base")
public class HotelBase {

    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    private Long hotelId;       // 酒店id
    private String hotelName;   // 酒店中文名
    private String hotelEngName;// 酒店英文名
    private String address;     // 酒店地址
    private String hotelIntroduce;  // 酒店介绍
    private String telephone;   // 酒店电话
    private String fax;         // 酒店传真
    private String postCode;    // 邮政编码
    private Integer hotelStar;  // 酒店星级 Integer 酒店星级，见【酒店星级】常量
    private Date praciceDate;   // 开业日期
    private Date fitmentDate;   // 装修日期
    private String parentHotelGroup;  // 酒店集团ID
    private String parentHotelGroupName;  // 酒店集团名称
    private String plateID;     // 所属品牌ID
    private String plateName;   // 品牌名称
    private String city;        // 城市
    private String distinct;    // 行政区
    private String business;    // 商业区
    private String longitude;   // 百度经度
    private String latitude;    // 百度纬度
    private String checkInTime;  // 酒店规定入住时间
    private String checkOutTime;  // 酒店规定退房时间
    private String roomNum;     // 酒店房间总数
    private String appearancePicUrl;  // 酒店主图HTTP URL地址

    private Integer status = 0;         // 酒店运营状态 0：正常 1-非正常(下架、暂停）
    private LocalDateTime createTime;
    private LocalDateTime lastModifyTime;

}