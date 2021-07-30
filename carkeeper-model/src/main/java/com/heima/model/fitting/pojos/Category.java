package com.heima.model.fitting.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * category
 * </p>
 * @author itheima
 */
@Data
@TableName("fitting_category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @ApiModelProperty("创建时间")
    @TableField("cteate_time")
	private java.util.Date cteateTime;//创建时间

    @ApiModelProperty("最后更新时间")
    @TableField("update_time")
	private java.util.Date updateTime;//最后更新时间



}
