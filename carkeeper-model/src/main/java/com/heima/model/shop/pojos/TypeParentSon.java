package com.heima.model.shop.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("serivce_type_parent_son")
public class TypeParentSon implements Serializable {

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id; //id

    @ApiModelProperty("父id")
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty("子id")
    @TableField("son_id")
    private Integer sonId;
}
