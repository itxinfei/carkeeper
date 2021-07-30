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
 * blog
 * </p>
 * @author itheima
 */
@Data
@TableName("tb_blog")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
	private Long id; //主键id

    @ApiModelProperty("标题")
    @TableField("title")
	private String title;//标题

    @ApiModelProperty("封面图片")
    @TableField("cover")
	private String cover;//封面图片

    @ApiModelProperty("分类id")
    @TableField("blog_category_id")
	private Long blogCategoryId;//分类id

    @ApiModelProperty("排序")
    @TableField("sort")
	private Integer sort;//排序

    @ApiModelProperty("链接")
    @TableField("link")
	private String link;//链接

    @ApiModelProperty("内容主体")
    @TableField("content")
	private String content;//内容主体

    @ApiModelProperty("状态：1为未发布，0为已发布")
    @TableField("status")
	private Short status;//状态：1为未发布，0为已发布

    @ApiModelProperty("发布人")
    @TableField("creator")
	private String creator;//发布人

    @ApiModelProperty("创建时间")
    @TableField("create_time")
	private Date createTime;//创建时间

    @ApiModelProperty("更新时间")
    @TableField("update_time")
	private Date updateTime;//更新时间

}
