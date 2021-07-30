package com.heima.model.common.constants;

public class WemediaContants {

    // 是否收藏
    public static final Short COLLECT_MATERIAL = 1;//收藏
    public static final Short CANCEL_COLLECT_MATERIAL = 0;//取消收藏

    // 文章类型
    public static final String WM_NEWS_TYPE_IMAGE = "image";
    public static final String WM_NEWS_TYPE_TEXT = "text";

    // 文章状态
    public static final Short WM_NEWS_DRAFT_STATUS = 0; //草稿
    public static final Short WM_NEWS_SUMMIT_STATUS = 1; //提交
    public static final Short WM_NEWS_AUTHED_STATUS = 8; //审核通过
    public static final Short WM_NEWS_PUBLISH_STATUS = 9; //已发布
    public static final Short WM_NEWS_AUTH_PASS = 4;
    public static final Short WM_NEWS_AUTH_FAIL = 2;

    // 文章封面选图
    public static final Short WM_NEWS_NONE_IMAGE = 0; //无图
    public static final Short WM_NEWS_SINGLE_IMAGE = 1; //单图
    public static final Short WM_NEWS_MANY_IMAGE = 3; //多图
    public static final Short WM_NEWS_TYPE_AUTO = -1; //图文类型自动

    // 文章图片引用
    public static final Short WM_CONTENT_REFERENCE = 0;
    public static final Short WM_IMAGE_REFERENCE = 1;

    public static final Short WM_NEWS_UPENABLE = 1;


}