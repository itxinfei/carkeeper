package com.heima.model.shop.dtos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.heima.model.common.dtos.PageRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Description: TypeSonDto
 * @Version: V1.0
 */
@Data
@ApiModel("TypeSonDto")
public class TypeSonDto extends PageRequestDto {

        @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
	private Long id; //id


    @ApiModelProperty("子分类名称")
    @TableField("name")
	private String name;//子分类名称

    private Integer parentId;

    @ApiModelProperty("子分类编号")
    @TableField("code")
	private String code;//子分类编号

    @ApiModelProperty("是否通用  0为通用 1为不通用")
    @TableField("common")
	private Boolean common;//是否通用  0为通用 1为不通用


}
