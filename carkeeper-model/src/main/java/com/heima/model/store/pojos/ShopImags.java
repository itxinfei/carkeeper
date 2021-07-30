package com.heima.model.store.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * shopImags
 * </p>
 * @author itheima
 */
@Data
@TableName("tb_shop_imags")
public class ShopImags implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
	private Integer id; //id



    @ApiModelProperty("imags")
    @TableField("imags")
	private String imags;//imags



}
