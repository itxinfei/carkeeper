package com.heima.model.common.comment.dtos;

import com.heima.model.common.dtos.PageRequestDto;
import lombok.Data;

import java.util.Date;

@Data
public class CommentServeDto extends PageRequestDto {

    private String orderNumber; //订单编号

    private String authorName;//客户名称

    private Short grade;//评分

    private String shop;//服务项目

    private Short classify;//服务分类

    private String beginPubDate;//开始时间

    private String endPubDate;//结束时间
}
