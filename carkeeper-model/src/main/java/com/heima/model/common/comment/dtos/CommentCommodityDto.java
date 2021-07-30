package com.heima.model.common.comment.dtos;

import com.heima.model.common.dtos.PageRequestDto;
import lombok.Data;

@Data
public class CommentCommodityDto extends PageRequestDto {

    private String orderNumber; //订单编号

    private String authorName;//客户名称

    private Short grade;//评分

    private String shopkeeper;//配件商

//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ssas")
//    @JsonFormat(pattern ="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private String beginPubDate;//开始时间

    private String endPubDate;//结束时间

}
