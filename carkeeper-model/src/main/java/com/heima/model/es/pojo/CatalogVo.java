package com.heima.model.es.pojo;

import lombok.Data;

/**
 * 新建接收服务索引库存储对象
 */
@Data
public class CatalogVo {

    /**
     * 服务目录id
     */
    private Long id; //catalogId

    private String name;//服务商名称

    private String sonCode;//服务项目编号

    private String sonName;//服务项目名称

    private String parentName;//服务分类

    private Integer price;//服务价格

    private char enable;//上下架

    private String special;//优惠活动
}
