package com.heima.comment.pojos;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Data
@Document("Carkeeper_Serve_Comment")
public class TbServeComment {


    /**
     * 主键id
     */
    private String id;

    /**
     * 门店id
     */
    private Long merchantId;

    /**
     * 用户ID 评论的用户ID（当前登录人ID）
     */
    private Integer authorId;

    /**
     * 服务分类
     */
    private Short classify;

    /**
     * 用户昵称
     */
    private String authorName;

    /**
     * 服务评论或是配件评论
     */
    private Short type;

//    /**
//     * 频道ID
//     */
//    private Integer channelId;

    /**
     * 评论内容
     */
    private String content;


    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 配件商/门店店主
     */
    private String shopkeeper;

    /**
     * 服务门店
     */
    private String shop;

    /**
     * 服务项目
     */
    private String serviceProject;

    /**
     * 登陆人头像
     */
    private String image;

    /**
     * 订单时间
     */
    private Date orderTime;

    /**
     * 文章标记
     * 0 普通评论
     * 1 热点评论
     * 2 推荐评论
     * 3 置顶评论
     * 4 精品评论
     */
    private Short flag;

    //综合分数
    private Short synthesize;

    /**
     * 服务态度/商品包装
     * 1 一分
     * 2 二分
     * 3 三分
     * 4 四分
     * 5 五分
     */
    private Short serviceScore;

    /**
     * 技师技术/商品质量
     * 1 一分
     * 2 二分
     * 3 三分
     * 4 四分
     * 5 五分
     */
    private Short skillScore;

    /**
     * 门店环境/发货速度
     * 1 一分
     * 2 二分
     * 3 三分
     * 4 四分
     * 5 五分
     */
    private Short surroundingsScore;

    /**
     * 评价图片
     */
    private String picture;

    /**
     * 点赞数
     */
    private Integer likes;

    /**
     * 回复数
     */
    private Integer reply;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;



}
