package com.heima.model.es.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.heima.model.common.annotation.IdEncrypt;
import com.heima.model.common.dtos.PageRequestDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CatalogSearchDto extends PageRequestDto {

    // 设备ID
    @IdEncrypt
    Integer equipmentId;
    /**
     * 搜索服务商名称
     */
    @ApiModelProperty("服务商名称")
    @TableField("name")
    private String name;//服务商名称


    @ApiModelProperty("服务项目编号")
    private String typeSonCode;

    @ApiModelProperty("服务项目名称")
    private String typeSonName;

    @ApiModelProperty("服务分类")
    private String typeParentName;


}
