package com.heima.model.store.dtos;

import com.heima.model.common.dtos.PageRequestDto;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Description: ShopImagsDto
 * @Version: V1.0
 */
@Data
@ApiModel("ShopImagsDto")
public class ShopImagsDto extends PageRequestDto {

        @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
	private Integer id; //id



        @ApiModelProperty("imags")
    @TableField("imags")
	private String imags;//imags


}
