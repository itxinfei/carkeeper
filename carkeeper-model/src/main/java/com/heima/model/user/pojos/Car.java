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
 * car
 * </p>
 * @author itheima
 */
@Data
@TableName("tb_car")
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
	private Long id; //id



    @ApiModelProperty("子品牌id")
    @TableField("brand_type_id")
	private Long brandTypeId;//子品牌id

    @ApiModelProperty("名称")
    @TableField("name")
	private String name;//名称

    @ApiModelProperty("车牌号")
    @TableField("code")
	private String code;//车牌号

    @ApiModelProperty("系列")
    @TableField("series")
	private String series;//系列

    @ApiModelProperty("型号")
    @TableField("mode")
	private String mode;//型号

    @ApiModelProperty("图片")
    @TableField("img_url")
	private String imgUrl;//图片

    @ApiModelProperty("发动机号")
    @TableField("engine_code")
	private String engineCode;//发动机号

    @ApiModelProperty("VIN码")
    @TableField("frame_code")
	private String frameCode;//VIN码

    @ApiModelProperty("保险公司id")
    @TableField("insurance_company_id")
	private Long insuranceCompanyId;//保险公司id

    @ApiModelProperty("保险期限")
    @TableField("insurance_finish_date")
	private java.util.Date insuranceFinishDate;//保险期限

    @ApiModelProperty("所属用户id")
    @TableField("user_id")
	private Long userId;//所属用户id

    @ApiModelProperty("是否默认")
    @TableField("is_default")
	private String isDefault;//是否默认



}
