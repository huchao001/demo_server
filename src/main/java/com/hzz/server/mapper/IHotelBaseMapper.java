package com.hzz.server.mapper;

import com.hzz.server.config.MyMapper;
import com.hzz.server.model.entity.HotelBase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 酒店基本信息
 * 才华有限公司 @author Administrator
 *
 * @create 2018/10/16
 **/
@Mapper
@Component
public interface IHotelBaseMapper extends MyMapper<HotelBase> {

    @Select(" select * from hotel_base where city = #{city} and hotel_star = #{hotelStar} ")
    @ResultType(Map.class)
    List<Map> listHotel(@Param("city") String city, @Param("hotelStar") Integer hotelStar);


}