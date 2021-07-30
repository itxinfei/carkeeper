package com.heima.model.shop.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("tb_service_detail")
public class ServiceDetail {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("服务描述")
    @TableField("service_info")
    private String serviceInfo;


    @ApiModelProperty("服务价格")
    @TableField("service_price")
    private Integer servicePrice;

    @ApiModelProperty("服务id")
    @TableField("service_id")
    private Long serviceId;
}
