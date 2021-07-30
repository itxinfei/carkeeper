package com.heima.model.order.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * serviceOrderLog
 * </p>
 * @author itheima
 */
@Data
@TableName("tb_service_order")
public class ServiceAndOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
	private Long id; //id


    @ApiModelProperty("订单id")
    @TableField("order_id")
	private Long orderId;

    @ApiModelProperty("服务id")
    @TableField("service_id")
	private Long serviceId;


}
