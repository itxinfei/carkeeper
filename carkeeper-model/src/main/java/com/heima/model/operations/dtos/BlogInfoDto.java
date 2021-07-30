package com.heima.model.operations.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.heima.model.common.dtos.PageRequestDto;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class BlogInfoDto extends PageRequestDto {

    private String title;//标题

    private Long blogCategoryId;//分类id

    private Short status;//状态：1为未发布，0为已发布

    private String creator;//发布人

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern ="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date createTimeMin;//小创建时间

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern ="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date createTimeMax;//大创建时间

}
