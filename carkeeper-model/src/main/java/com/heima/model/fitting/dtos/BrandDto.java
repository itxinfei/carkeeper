package com.heima.model.fitting.dtos;

import com.heima.model.common.dtos.PageRequestDto;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Description: BrandDto
 * @Version: V1.0
 */
@Data
@ApiModel("BrandDto")
public class BrandDto extends PageRequestDto {

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
