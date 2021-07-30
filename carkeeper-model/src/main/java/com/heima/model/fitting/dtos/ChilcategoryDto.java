package com.heima.model.fitting.dtos;

import com.heima.model.common.dtos.PageRequestDto;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Description: ChilcategoryDto
 * @Version: V1.0
 */
@Data
@ApiModel("ChilcategoryDto")
public class ChilcategoryDto extends PageRequestDto {

        @ApiModelProperty("主键Id")
    @TableId(value = "id", type = IdType.AUTO)
	private Integer id; //主键Id



        @ApiModelProperty("一级分类编码 ")
    @TableField("category_code")
	private Integer categoryCode;//一级分类编码 

    @ApiModelProperty("二级分类编码")
    @TableField("chilCategory_code")
	private Integer chilCategoryCode;//二级分类编码

    @ApiModelProperty("二级分类名称")
    @TableField("chilCategory_name")
	private String chilCategoryName;//二级分类名称

    @ApiModelProperty("排序")
    @TableField("sort")
	private Integer sort;//排序

    @ApiModelProperty("图片")
    @TableField("image")
	private String image;//图片

    @ApiModelProperty("创建时间")
    @TableField("create_time")
	private java.util.Date createTime;//创建时间

    @ApiModelProperty("最后创建时间")
    @TableField("update_time")
	private java.util.Date updateTime;//最后创建时间


}
