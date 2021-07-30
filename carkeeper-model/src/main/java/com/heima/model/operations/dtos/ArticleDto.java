package com.heima.model.operations.dtos;

import com.heima.model.common.dtos.PageRequestDto;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * @Description: ArticleDto
 * @Version: V1.0
 */
@Data
public class ArticleDto extends PageRequestDto {

	private Long id; //主键

	private String name;//推文名称

	private String image;//标题图片

	private String category;//所属分类（二级分类）

	private String content;//推文内容

    private Date createTime;//创建时间

    private Date updateTime;//更新时间

	private String classify;//一级分类

	private Byte type;//0服务，1配件
}
