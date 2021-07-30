package com.heima.model.shop.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * typeSon
 * </p>
 * @author itheima
 */
@Data
@TableName("service_type_son")
public class TypeSon implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
	private Long id; //id

    @ApiModelProperty("parentId")
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty("子分类名称")
    @TableField("name")
	private String name;//子分类名称

    @ApiModelProperty("子分类编号")
    @TableField("code")
	private String code;//子分类编号


    @ApiModelProperty("是否通用  0为通用 1为不通用")
    @TableField("common")
	private Boolean common;//是否通用  0为通用 1为不通用

    @ApiModelProperty("创建时间")
    @TableField("create_time")
	private java.util.Date createTime;//创建时间

    @ApiModelProperty("修改时间")
    @TableField("update_time")
	private java.util.Date updateTime;//修改时间



}
