package com.heima.model.operations.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * blogCategory
 * </p>
 * @author itheima
 */
@Data
@TableName("tb_blog_category")
public class BlogCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
	private Long id; //主键id

    @ApiModelProperty("分类名称")
    @TableField("name")
	private String name;//分类名称

    @ApiModelProperty("描述")
    @TableField("description")
	private String description;//描述

    @ApiModelProperty("创建时间")
    @TableField("create_time")
	private Date createTime;//创建时间

    @ApiModelProperty("更新时间")
    @TableField("update_time")
	private Date updateTime;//更新时间



}
