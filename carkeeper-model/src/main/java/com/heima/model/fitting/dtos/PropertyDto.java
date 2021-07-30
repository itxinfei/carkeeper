package com.heima.model.fitting.dtos;

import com.heima.model.common.dtos.PageRequestDto;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Description: PropertyDto
 * @Version: V1.0
 */
@Data
@ApiModel("PropertyDto")
public class  PropertyDto extends PageRequestDto {

        @ApiModelProperty("主键Id")
    @TableId(value = "id", type = IdType.AUTO)
	private Integer id; //主键Id


    @ApiModelProperty("二级分类id")
    @TableField("chilCategory_id")
	private Integer chilCategoryId;//二级分类id

    @ApiModelProperty("属性名称")
    @TableField("attribute_name")
	private String attributeName;//属性名称

    @ApiModelProperty("计量单位")
    @TableField("unit")
	private String unit;//计量单位

    @ApiModelProperty("选项: 多选项用@隔开(文本框没有选项)")
    @TableField("tab_option")
	private String option;//选项: 多选项用@隔开(文本框没有选项)

    @ApiModelProperty("默认选项")
    @TableField("default_option")
	private String defaultOption;//默认选项

    @ApiModelProperty("是否可搜索")
    @TableField("is_search")
	private Byte isSearch;//是否可搜索

    @ApiModelProperty("是否必填")
    @TableField("is_must")
	private Byte isMust;//是否必填

    @ApiModelProperty("选项: 文本框: 1 下拉框：2 单选框: 3 复选框 4 多行文本框：5")
    @TableField("from_type")
	private Integer fromType;//选项: 文本框: 1 下拉框：2 单选框: 3 复选框 4 多行文本框：5

    @ApiModelProperty("创建时间")
    @TableField("create_time")
	private java.util.Date createTime;//创建时间

    @ApiModelProperty("最后更新时间")
    @TableField("update_time")
	private java.util.Date updateTime;//最后更新时间


}
