package com.heima.model.operations.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * blogComponent
 * </p>
 * @author itheima
 */
@Data
@TableName("tb_blog_component")
public class BlogComponent implements Serializable {

    private static final long serialVersionUID = 1L;

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
