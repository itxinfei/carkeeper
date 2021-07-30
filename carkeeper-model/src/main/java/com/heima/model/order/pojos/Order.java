package com.heima.model.order.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * order
 * </p>
 * @author itheima
 */
@Data
@TableName("tb_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
	private Long id; //id

    @ApiModelProperty("订单编号")
    @TableField("code")
	private String code;//订单编号

    @ApiModelProperty("店铺id")
    @TableField("store_id")
	private Long storeId;//店铺id

    @ApiModelProperty("店铺名称")
    @TableField("store_name")
	private String storeName;//店铺名称

    @ApiModelProperty("店铺电话")
    @TableField("store_tel")
	private String storeTel;//店铺电话

    @ApiModelProperty("操作员id")
    @TableField("user_id")
	private Long userId;//

    @ApiModelProperty("客户名称")
    @TableField("contact")
	private String contact;//客户名称

    @ApiModelProperty("客户电话")
    @TableField("contact_tel")
	private String contactTel;//客户电话

    @ApiModelProperty("创建日期")
    @TableField("create_date")
	private Date createDate;//创建日期

    @ApiModelProperty("服务日期")
    @TableField("service_date")
	private Date serviceDate;//服务日期

    @ApiModelProperty("支付日期")
    @TableField("pay_date")
	private Date payDate;//支付日期

    @ApiModelProperty("订单金额（分）")
    @TableField("money")
	private Integer money;//订单金额（分）

    @ApiModelProperty("类型 1 服务 2配件")
    @TableField("type")
	private Integer type;//类型 1 服务 2配件

    @ApiModelProperty("支付状态   0未支付  1已支付")
    @TableField("pay_status")
	private Integer payStatus;//支付状态

    @ApiModelProperty("订单状态  0待处理 1待服务 2待评价  3已完成")
    @TableField("order_status")
	private Integer orderStatus;//订单状态

    @ApiModelProperty("收货方id")
    @TableField("receiver_id")
    private Long receiverId;

    @ApiModelProperty("服务车主id")
    @TableField("car_id")
    private Long carId;

    @ApiModelProperty("订单是否关闭")
    @TableField("is_close")
    private Integer isClose;


}
