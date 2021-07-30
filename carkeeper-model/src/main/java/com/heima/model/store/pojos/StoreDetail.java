package com.heima.model.store.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * storeDetail
 * </p>
 * @author itheima
 */
@Data
@TableName("tb_store_detail")
public class StoreDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("门店id")
    @TableId(value = "id", type = IdType.AUTO)
	private Long id; //门店id



    @ApiModelProperty("标签列表")
    @TableField("labels")
	private String labels;//标签列表

    @ApiModelProperty("营业执照图片")
    @TableField("business_license_url")
	private String businessLicenseUrl;//营业执照图片

    @ApiModelProperty("法人代表正面图片")
    @TableField("corporation_obverse_url")
	private String corporationObverseUrl;//法人代表正面图片

    @ApiModelProperty("法人代表背面图片")
    @TableField("corporation_back_url")
	private String corporationBackUrl;//法人代表背面图片

    @ApiModelProperty("营业时间")
    @TableField("business_hours")
	private String businessHours;//营业时间

    @ApiModelProperty("客服电话")
    @TableField("business_phone")
	private String businessPhone;//客服电话

    @ApiModelProperty("支持救援")
    @TableField("is_rescue")
	private String isRescue;//支持救援

    @ApiModelProperty("救援时间")
    @TableField("rescue_hours")
	private String rescueHours;//救援时间

    @ApiModelProperty("救援电话")
    @TableField("rescue_phone")
	private String rescuePhone;//救援电话

    @ApiModelProperty("公司简介")
    @TableField("introduce")
	private String introduce;//公司简介



}
