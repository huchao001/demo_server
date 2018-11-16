package com.hzz.server.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

/**
 * 酒店房型
 * 才华有限公司 @author Administrator
 *
 * @create 2018/10/10
 **/
@Data
@Table(name = "hotel_room_info")
public class RoomInfo {

    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    private Long hotelId;
    private Long roomId;    // 房型名称
    private String roomName;// 房型名称
    private String roomEngName; // 房型英文名称
    private String roomAcreage; // 房间面积
    private String roomFloor;   // 房间楼层
    private String maxPerson;    // 最多入住人
    private String maxChild;    // 最多入住儿童数
    private String broadNet;    // 宽带Integer 1：收费，2：免费，3:无，4：未设置
    private String addBedflag;    // 是否可加床 Integer 0：不可加床 1：可加床 2：部分可加床
    private String addBedFee;    // 加床费用
    private String remark;    // 备注

}



