package com.heima.model.store.pojos;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * store
 * </p>
 * @author itheima
 */
@Data
@TableName("tb_store")
public class Store implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("门店编码")
    @TableField(value = "code" )
    private Long code; //门店编码



    @ApiModelProperty("id")
    @TableField("id")
    private Long id;//id


    @ApiModelProperty("门店全称")
    @TableField("name")
	private String name;//门店全称

    @ApiModelProperty("门店简称")
    @TableField("simple_name")
	private String simpleName;//门店简称


    @ApiModelProperty("门店图片")
    @TableField("img_url")
	private String imgUrl;//门店图片

    @ApiModelProperty("省")
    @TableField("province")
	private String province;//省

    @ApiModelProperty("市")
    @TableField("city")
	private String city;//市

    @ApiModelProperty("区县")
    @TableField("county")
	private String county;//区县

    @ApiModelProperty("地址")
    @TableField("address")
	private String address;//地址

    @ApiModelProperty("经度")
    @TableField("address_longitude")
	private Double addressLongitude;//经度

    @ApiModelProperty("维度")
    @TableField("address_latitude")
	private Double addressLatitude;//维度

    @ApiModelProperty("城市编码")
    @TableField("city_code")
	private String cityCode;//城市编码

    @ApiModelProperty("联系人")
    @TableField("contact_name")
	private String contactName;//联系人

    @ApiModelProperty("联系电话")
    @TableField("contact_phone")
	private String contactPhone;//联系电话

    @ApiModelProperty("类型标签")
    @TableField("type_lab")
	private Integer typeLab;//类型标签

    @ApiModelProperty("类别 1汽修厂 2配件商")
    @TableField("type")
	private String type;//类别 1汽修厂 2配件商

    @ApiModelProperty("是否连锁")
    @TableField("is_chain")
	private String isChain;//是否连锁

    @ApiModelProperty("来源")
    @TableField("source")
	private String source;//来源

    @ApiModelProperty("状态")
    @TableField("status")
	private String status;//状态

    @ApiModelProperty("创建状态")
    @TableField("flag")
    private Short flag;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("营业执照照片")
    @TableField("business_license_url")
    private String businessLicenseUrl;

    @ApiModelProperty("法人代表正面照片")
    @TableField("corporation_obverse_url")
    private String corporationObverseUrl;

    @ApiModelProperty("法人代表背面照片")
    @TableField("corporation_back_url")
    private String corporationBackUrl;






}
