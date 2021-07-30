package com.heima.model.shop.dtos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.heima.model.common.dtos.PageRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Description: CatalogDto
 * @Version: V1.0
 */
@Data
@ApiModel("CatalogDto")
public class CatalogDto extends PageRequestDto {

    @ApiModelProperty("服务商名称")
    @TableField("name")
	private String name;//服务商名称


    @ApiModelProperty("服务项目编号")
	private String typeSonCode;

    @ApiModelProperty("服务项目名称")
	private String typeSonName;

    @ApiModelProperty("服务分类")
	private String typeParentName;


}
