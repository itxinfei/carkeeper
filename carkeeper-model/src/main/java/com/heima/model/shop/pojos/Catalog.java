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
 * catalog
 * </p>
 * @author itheima
 */
@Data
@TableName("service_catalog")
public class Catalog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
	private Long id; //id

    @ApiModelProperty("special")
    @TableField("special")
    private String special;

    @ApiModelProperty("服务商名称")
    @TableField("name")
	private String name;//服务商名称

    @ApiModelProperty("价格")
    @TableField("price")
	private Integer price;//价格

    @ApiModelProperty("上下架 0为下架 1为上架")
    @TableField("enable")
	private Short enable;//上下架 0为下架 1为上架

    @ApiModelProperty("parent_son_id")
    @TableField("parent_son_id")
	private Long parentSonId;



}
