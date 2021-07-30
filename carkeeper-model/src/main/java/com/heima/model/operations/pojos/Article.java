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
 * article
 * </p>
 * @author itheima
 */
@Data
@TableName("tb_article")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
	private Long id; //主键

    @ApiModelProperty("推文名称")
    @TableField("name")
	private String name;//推文名称

    @ApiModelProperty("标题图片")
    @TableField("image")
	private String image;//标题图片

    @ApiModelProperty("所属分类（二级分类）")
    @TableField("category")
	private String category;//所属分类（二级分类）

    @ApiModelProperty("推文内容")
    @TableField("content")
	private String content;//推文内容

    @ApiModelProperty("创建时间")
    @TableField("create_time")
	private Date createTime;//创建时间

    @ApiModelProperty("更新时间")
    @TableField("update_time")
	private Date updateTime;//更新时间

    @ApiModelProperty("一级分类")
    @TableField("classify")
	private String classify;//一级分类

    @ApiModelProperty("0服务，1配件")
    @TableField("type")
	private Byte type;//0服务，1配件



}
