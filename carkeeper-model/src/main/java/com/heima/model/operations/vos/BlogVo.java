package com.heima.model.operations.vos;

import lombok.Data;

import java.util.Date;

@Data
public class BlogVo {

    private String title;//标题

    private Date createTime;//创建时间

    private String name;//分类名称

    private String creator;//发布人

    private Short status;//状态：1为未发布，0为已发布

    private Integer sort;//排序
}
