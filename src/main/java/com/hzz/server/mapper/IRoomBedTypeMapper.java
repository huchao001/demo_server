package com.hzz.server.mapper;

import com.hzz.server.config.MyMapper;
import com.hzz.server.model.entity.RoomBedType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 才华有限公司 @author Administrator
 *
 * @create 2018/10/15
 **/
@Mapper
@Component
public interface IRoomBedTypeMapper extends MyMapper<RoomBedType> {

    @Select(" select * from room_bed_type where hotel_id = #{hotelId} and room_id = #{roomId} ")
    @ResultType(Map.class)
    List<Map> listByHotelRoomId(@Param("hotelId") Long hotelId, @Param("roomId") Long roomId);

    @Select(" select * from room_bed_type where hotel_id = #{hotelId}")
    @Results(id = "roomBedTypeMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "hotelId", column = "hotel_id"),
            @Result(property = "roomId", column = "room_id"),
            @Result(property = "bedCode", column = "bed_code"),
            @Result(property = "bedCount", column = "bed_count"),
            @Result(property = "bedWidth", column = "bed_width"),
            @Result(property = "bedLength", column = "bed_length"),
    })
    List<RoomBedType> listByHotelRoomIds(@Param("hotelId") Long hotelId);

    @Select(" select * from room_bed_type where hotel_id = #{hotelId} and room_id = #{roomId} ")
    @ResultMap("roomBedTypeMap")
    List<RoomBedType> listByHotelRoomIds2(@Param("hotelId") Long hotelId, @Param("roomId") Long roomId);

    @Select(" select * from room_bed_type where id = #{id}")
    @ResultMap("roomBedTypeMap")
    RoomBedType queryById(@Param("id") Long id);

}
