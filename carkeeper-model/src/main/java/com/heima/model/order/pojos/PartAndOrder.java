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
 * partOrder
 * </p>
 * @author itheima
 */
@Data
@TableName("tb_part_order")
public class PartAndOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("配件与订单中间表id")
    @TableId(value = "id", type = IdType.AUTO)
	private Long id; //配件与订单中间表id



    @ApiModelProperty("配件id")
    @TableField("part_id")
	private Long partId;//配件id

    @ApiModelProperty("订单id")
    @TableField("order_id")
	private Long orderId;//订单id



}
