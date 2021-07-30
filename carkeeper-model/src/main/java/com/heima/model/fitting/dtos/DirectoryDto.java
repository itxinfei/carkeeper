package com.heima.model.fitting.dtos;

import com.heima.model.common.dtos.PageRequestDto;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Description: DirectoryDto
 * @Version: V1.0
 */
@Data
@ApiModel("DirectoryDto")
public class DirectoryDto extends PageRequestDto {

    @ApiModelProperty("主键Id")
    @TableId(value = "id", type = IdType.AUTO)
	private Integer id; //主键Id


    @ApiModelProperty("配件商编码")
    @TableField("merchant_code")
    private String merchantCode;//配件商编码

    @ApiModelProperty("配件商")
    @TableField("fitting_merchant")
	private String fittingMerchant;//配件商

    @ApiModelProperty("配件编码")
    @TableField("fitting_code")
	private Integer fittingCode;//配件Id

    @ApiModelProperty("配件名称")
    @TableField("fitting_name")
	private String fittingName;//配件名称

    @ApiModelProperty("规格型号")
    @TableField("specification")
	private String specification;//规格型号

    @ApiModelProperty("二级配件分类Id")
    @TableField("fitting_chilCategoryId")
	private Integer fittingChilCategoryId;//二级配件分类Id

    @ApiModelProperty("二级配件分类名字")
    @TableField("fitting_chilCategoryName")
    private Integer fittingChilCategoryName;//二级配件分类名字

    @ApiModelProperty("配件品牌Id")
    @TableField("brand_id")
	private Integer brandId;//配件品牌Id

    @ApiModelProperty("配件品牌名字")
    @TableField("brand_name")
    private Integer brandName;//配件品牌名字

    @ApiModelProperty("价格")
    @TableField("price")
	private Double price;//价格

    @ApiModelProperty("是否下架")
    @TableField("is_down")
	private Byte isDown;//是否下架

    @ApiModelProperty("创建时间")
    @TableField("create_time")
	private java.util.Date createTime;//创建时间

    @ApiModelProperty("最后更新时间")
    @TableField("update_time")
	private java.util.Date updateTime;//最后更新时间


}
