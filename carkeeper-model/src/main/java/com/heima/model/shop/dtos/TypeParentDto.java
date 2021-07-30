package com.heima.model.shop.dtos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.heima.model.common.dtos.PageRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Description: TypeParentDto
 * @Version: V1.0
 */
@Data
@ApiModel("TypeParentDto")
public class TypeParentDto extends PageRequestDto {

        @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
	private Long id; //id



        @ApiModelProperty("分类编码")
    @TableField("code")
	private String code;//分类编码

    @ApiModelProperty("分类名称")
    @TableField("name")
	private String name;//分类名称

    @ApiModelProperty("创建时间")
    @TableField("create_time")
	private java.util.Date createTime;//创建时间

    @ApiModelProperty("修改时间")
    @TableField("update_time")
	private java.util.Date updateTime;//修改时间


}
