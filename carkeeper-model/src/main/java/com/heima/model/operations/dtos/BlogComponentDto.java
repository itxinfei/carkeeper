package com.heima.model.operations.dtos;

import com.heima.model.common.dtos.PageRequestDto;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Description: BlogComponentDto
 * @Version: V1.0
 */
@Data
@ApiModel("BlogComponentDto")
public class BlogComponentDto extends PageRequestDto {

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
	private Long id; //主键id

    @ApiModelProperty("文章id")
    @TableField("blog_id")
	private Long blogId;//文章id

    @ApiModelProperty("配件id")
    @TableField("component_id")
	private Long componentId;//配件id


}
