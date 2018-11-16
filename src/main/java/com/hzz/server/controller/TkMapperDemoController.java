package com.hzz.server.controller;

import com.google.common.collect.ImmutableMap;
import com.hzz.server.common.AjaxResult;
import com.hzz.server.mapper.IRoomBedTypeMapper;
import com.hzz.server.model.entity.RoomBedType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * MyBatis示例
 * 才华有限公司 @author Administrator
 *
 * @create 2018/11/2
 **/
@RestController
@RequestMapping("/ibatis")
@ApiResponses({@ApiResponse(code = 200, message = "调用成功"), @ApiResponse(code = 400, message = "业务异常"),
        @ApiResponse(code = 401, message = "您没有登录"), @ApiResponse(code = 403, message = "权限不够,不允许访问"),
        @ApiResponse(code = 405, message = "请求的方法不支持"), @ApiResponse(code = 500, message = "系统内部错误"),
        @ApiResponse(code = 502, message = "网关超时"), @ApiResponse(code = 503, message = "服务不可达")})
@Api(value = "TkMapperDemoController", tags = "TkMapperDemoController", description = "Mybatis示例", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TkMapperDemoController {
    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Resource
    private IRoomBedTypeMapper roomBedTypeMapper;

    @ApiOperation(value = "条件查询", notes = "条件查询")
    @RequestMapping(value = "/queryCondition", method = {RequestMethod.GET})
    @ResponseBody
    public AjaxResult queryCondition() {
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("【Mybatis示例】,条件查询（queryCondition）");
        }

        Example param = new Example(RoomBedType.class);
        Example.Criteria criteria = param.createCriteria();
        criteria.andNotEqualTo("bedCode","y");

        List<RoomBedType> data = roomBedTypeMapper.selectByExample(param);

        return AjaxResult.success(data);
    }


    @ApiOperation(value = "分页查询", notes = "分页查询")
    @RequestMapping(value = "/queryRowBounds", method = {RequestMethod.GET})
    @ResponseBody
    public AjaxResult queryRowBounds(int pageNo, int pageSize) {
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("【Mybatis示例】,传统分页查询（queryRowBounds）");
        }

        int limit = pageSize < 1 ? 10 : pageSize;
        int offset = (pageNo < 1 ? 0 : pageNo - 1) * limit;

        RoomBedType param = new RoomBedType();
        RowBounds rowBounds = new RowBounds(offset, limit);
        List<RoomBedType> data0 = roomBedTypeMapper.selectByRowBounds(param, rowBounds);

        // 倒序
        Example example = new Example(RoomBedType.class);
        example.setOrderByClause("id desc");
        List<RoomBedType> data = roomBedTypeMapper.selectByExampleAndRowBounds(example,rowBounds);

        int count0 = roomBedTypeMapper.selectCount(param);
        int count = roomBedTypeMapper.selectCountByExample(example);

        Map ret = ImmutableMap.of("total", count, "curPage", pageNo, "listData", data);

        return AjaxResult.success(ret);
    }

    @ApiOperation(value = "增删改查", notes = "增删改查")
    @RequestMapping(value = "/crud", method = {RequestMethod.GET})
    @ResponseBody
    public AjaxResult crud() {
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("【Mybatis示例】,单表增删改查（crud）");
        }

        // 新增
        RoomBedType addParam = new RoomBedType();

        addParam.setId(13L);
        addParam.setHotelId(1020030002L);
        addParam.setRoomId(1001L);
        addParam.setBedCode("y");
        addParam.setBedCount(2);
        addParam.setBedWidth("180cm");
        addParam.setBedLength("200cm");

        int insertRet = roomBedTypeMapper.insert(addParam);

        // 查询
        RoomBedType param = new RoomBedType();
        param.setId(13L);
        RoomBedType afterAdd = roomBedTypeMapper.selectOne(param);

        // 修改
        RoomBedType updateParam = new RoomBedType();
        updateParam.setId(13L);
        updateParam.setBedWidth("test.update2");

        Example example = new Example(RoomBedType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",13L);
        int updateRetAl = roomBedTypeMapper.updateByExampleSelective(updateParam,example);

        RoomBedType delParam = new RoomBedType();
        delParam.setId(13L);
        int delRet = roomBedTypeMapper.delete(delParam);

        RoomBedType afterUpdate = roomBedTypeMapper.selectOne(param);

        LOGGER.debug(afterAdd + "," + afterUpdate);

        return AjaxResult.success(afterUpdate);
    }


// 等号的CRUD:
// * List<T> select(T record); 根据实体中的属性值进行查询，查询条件使用等号
// * T selectByPrimaryKey(Object key); 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
// * List<T> selectAll(); 查询全部结果，select(null)方法能达到同样的效果
// * T selectOne(T record); 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
// * int selectCount(T record); 根据实体中的属性查询总数，查询条件使用等号
// * int insert(T record); 保存一个实体，null的属性也会保存，不会使用数据库默认值
// * int insertSelective(T record); 保存一个实体，null的属性不会保存，会使用数据库默认值
// * int updateByPrimaryKey(T record); 根据主键更新实体全部字段，null值会被更新
// * int updateByPrimaryKeySelective(T record); 根据主键更新属性不为null的值
// * int delete(T record); 根据实体属性作为条件进行删除，查询条件使用等号
// * int deleteByPrimaryKey(Object key); 根据主键字段进行删除，方法参数必须包含完整的主键属性
}
