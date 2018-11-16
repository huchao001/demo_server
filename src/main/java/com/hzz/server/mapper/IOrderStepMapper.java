package com.hzz.server.mapper;

import com.hzz.server.config.MyMapper;
import com.hzz.server.model.entity.OrderStep;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface IOrderStepMapper extends MyMapper<OrderStep> {
}
