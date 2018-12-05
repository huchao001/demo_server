package com.hzz.server.model.dto;

import com.hzz.server.common.validator.IDCardNo;
import com.hzz.server.common.validator.Phone;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 入参请求示例
 */
@ApiModel(value="入参示例",description="入参示例（0）")
@Data
public class CommonReqDTO implements Serializable {

    @ApiModelProperty(value = "请求号不能为空", required = true)
    private  String requestNo;

    @ApiModelProperty(value = "金额,单位:分", required = true)
    @NotNull(message = "金额不能为空")
    @Range(min = 1,max = 999900,message = "金额范围:1分~9999元")
    private  Long payAmount;

    @ApiModelProperty(value = "身份证号", required = true)
    @NotNull(message="证件号码不能为空")
    @IDCardNo(message="身份证号码不合法")
    private String IdNo;

    @ApiModelProperty(value = "手机号码", required = true)
    @NotNull(message="手机号不能为空")
    @Phone
    private  String mobile;

}
