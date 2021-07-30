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
 * part
 * </p>
 * @author itheima
 */
@Data
@TableName("tb_part")
public class Part implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("配件id")
    @TableId(value = "id", type = IdType.AUTO)
	private Long id; //配件id



    @ApiModelProperty("订单id")
    @TableField("order_id")
	private Long orderId;//订单id

    @ApiModelProperty("商品编号")
    @TableField("shop_code")
	private String shopCode;//商品编号

    @ApiModelProperty("商品名称")
    @TableField("shop_name")
	private String shopName;//商品名称

    @ApiModelProperty("规格型号")
    @TableField("specification_type")
	private String specificationType;//规格型号

    @ApiModelProperty("出厂编码")
    @TableField("factory_code")
	private String factoryCode;//出厂编码

    @ApiModelProperty("品牌")
    @TableField("brand_name")
	private String brandName;//品牌

    @ApiModelProperty("订货价")
    @TableField("part_pricing")
	private Integer partPricing;//订货价

    @ApiModelProperty("订货量")
    @TableField("part_number")
	private Integer partNumber;//订货量

    @ApiModelProperty("金额")
    @TableField("part_money")
	private Integer partMoney;//金额



}
