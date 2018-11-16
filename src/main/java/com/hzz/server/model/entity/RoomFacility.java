package com.hzz.server.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

/**
 * 房型设施<列表>
 */
@Data
@Table(name = "room_facility")
class RoomFacility {

    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    private Long hotelId;

    private Long roomId;

    private String facilityCode;    // 房型设施编码
    private String facilityName;    // 房型设施名称
    private String content;         // 房型设施描述
}