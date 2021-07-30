package com.heima.model.fitting.dtos;

import com.heima.model.common.dtos.PageRequestDto;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Description: CategoryDto
 * @Version: V1.0
 */
@Data
@ApiModel("CategoryDto")
public class CategoryDto extends PageRequestDto {

        @ApiModelProperty("主键id ")
    @TableId(value = "id", type = IdType.AUTO)
	private Integer id; //主键id 



        @ApiModelProperty("配件分类编码")
    @TableField("category_code")
	private Integer categoryCode;//配件分类编码

    @ApiModelProperty("配件分类名称")
    @TableField("category_name")
	private String categoryName;//配件分类名称

    @ApiModelProperty("类型")
    @TableField("type")
	private String type;//类型

    @ApiModelProperty("排序")
    @TableField("sort")
	private Integer sort;//排序
}
