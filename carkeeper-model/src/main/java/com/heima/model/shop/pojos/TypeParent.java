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
 * typeParent
 * </p>
 * @author itheima
 */
@Data
@TableName("service_type_parent")
public class TypeParent implements Serializable {

    private static final long serialVersionUID = 1L;

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
