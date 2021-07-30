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
 * property
 * </p>
 * @author itheima
 */
@Data
@TableName("fitting_type")
public class Type implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键Id")
    @TableId(value = "id", type = IdType.AUTO)
	private Integer id; //主键Id

    @ApiModelProperty("配件类型")
    @TableField("Type")
	private Integer type;//二级分类id

    @ApiModelProperty("创建时间")
    @TableField("create_time")
	private java.util.Date createTime;//创建时间

    @ApiModelProperty("最后更新时间")
    @TableField("update_time")
	private java.util.Date updateTime;//最后更新时间

}
