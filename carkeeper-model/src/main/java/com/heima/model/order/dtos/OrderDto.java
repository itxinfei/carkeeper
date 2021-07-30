package com.heima.model.order.dtos;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.heima.model.common.dtos.PageRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * @Description: OrderDto
 * @Version: V1.0
 */

@Data
@ApiModel("OrderDto")
public class OrderDto extends PageRequestDto {

    @ApiModelProperty("订单编号")
    @TableField("code")
	private String code;//订单编号

    @ApiModelProperty("店铺名称")
    @TableField("store_name")
	private String storeName;//店铺名称

    @ApiModelProperty("客户名称")
    @TableField("contact")
	private String contact;//客户名称

    @ApiModelProperty("查询订单起始日期")
    @TableField("create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern ="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
	private Date beginDate;

    @ApiModelProperty("查询订单末尾日期")
    @TableField("service_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern ="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
	private Date overDate;

    @ApiModelProperty("订单状态")
    @TableField("order_status")
	private Integer orderStatus;//订单状态

    @ApiModelProperty("支付状态")
    @TableField("pay_status")
    private Integer payStatus;//支付状态

    @ApiModelProperty("类型 1 服务 2配件")
    @TableField("type")
    private Integer type;//类型 1 服务 2配件



}
