package com.heima.model.user.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * receiver
 * </p>
 * @author itheima
 */
@Data
@TableName("tb_receiver")
public class Receiver implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("收货方id")
    @TableId(value = "id", type = IdType.AUTO)
	private Long id; //收货方id



    @ApiModelProperty("收货方名称")
    @TableField("receiver_name")
	private String receiverName;//收货方名称

    @ApiModelProperty("行政区域")
    @TableField("administrative_place")
	private String administrativePlace;//行政区域

    @ApiModelProperty("收货方地址")
    @TableField("receiver_place")
	private String receiverPlace;//收货方地址

    @ApiModelProperty("配送方式")
    @TableField("send_way")
	private String sendWay;//配送方式

    @ApiModelProperty("支付方式")
    @TableField("pay_way")
	private String payWay;//支付方式



}
