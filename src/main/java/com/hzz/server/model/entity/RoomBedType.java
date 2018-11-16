package com.hzz.server.model.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * 房间床型
 * 才华有限公司 @author Administrator
 *
 * @create 2018/10/15
 **/
@Data
@Table(name = "room_bed_type")
public class RoomBedType {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long hotelId;

    private Long roomId;

    private String bedCode;    // 床型编码

    private Integer bedCount;   // 床数

    private String bedWidth;    // '床宽'

    private String bedLength;   // '床长'

    public Long getId() {
        return id;
    }

    public RoomBedType setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public RoomBedType setHotelId(Long hotelId) {
        this.hotelId = hotelId;
        return this;
    }

    public Long getRoomId() {
        return roomId;
    }

    public RoomBedType setRoomId(Long roomId) {
        this.roomId = roomId;
        return this;
    }

    public String getBedCode() {
        return bedCode;
    }

    public RoomBedType setBedCode(String bedCode) {
        this.bedCode = bedCode;
        return this;
    }

    public Integer getBedCount() {
        return bedCount;
    }

    public RoomBedType setBedCount(Integer bedCount) {
        this.bedCount = bedCount;
        return this;
    }

    public String getBedWidth() {
        return bedWidth;
    }

    public RoomBedType setBedWidth(String bedWidth) {
        this.bedWidth = bedWidth;
        return this;
    }

    public String getBedLength() {
        return bedLength;
    }

    public RoomBedType setBedLength(String bedLength) {
        this.bedLength = bedLength;
        return this;
    }
}
