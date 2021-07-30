package com.heima.model.fitting.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * brand
 * </p>
 * @author itheima
 */
@Data
@TableName("fitting_brand")
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
	private Integer id; //主键id



    @ApiModelProperty("品牌编码")
    @TableField("brand_code")
	private Integer brandCode;//品牌编码

    @ApiModelProperty("品牌名称")
    @TableField("brand_name")
	private String brandName;//品牌名称

    @ApiModelProperty("排序")
    @TableField("sort")
	private Integer sort;//排序

    @ApiModelProperty("图片url")
    @TableField("image")
	private String image;//图片url

    @ApiModelProperty("创建时间")
    @TableField("create_time")
	private java.util.Date createTime;//创建时间

    @ApiModelProperty("最后更新时间")
    @TableField("update_time")
	private java.util.Date updateTime;//最后更新时间



}
