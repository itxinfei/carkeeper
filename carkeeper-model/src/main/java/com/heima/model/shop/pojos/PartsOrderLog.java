package com.heima.model.shop.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * partsOrderLog
 * </p>
 * @author itheima
 */
@Data
@TableName("tb_part_order_log")
public class PartsOrderLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
	private Long id; //id

    @ApiModelProperty("操作日期")
    @TableField("handle_time")
	private java.util.Date handleTime;//操作日期

    @ApiModelProperty("描述")
    @TableField("description")
	private String description;//描述

    @ApiModelProperty("订单状态")
    @TableField("order_status")
	private String orderStatus;//订单状态

    @ApiModelProperty("订单id")
    @TableField("order_id")
	private Long orderId;//订单id

    @ApiModelProperty("操作员id")
    @TableField("user_id")
	private Long userId;//操作员id

    @ApiModelProperty("操作员名称")
    @TableField("user_realname")
	private String userRealname;//操作员名称



}
