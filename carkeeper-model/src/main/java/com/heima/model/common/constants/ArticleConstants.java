package com.heima.model.common.constants;

public class ArticleConstants {
    public static final Short LOADTYPE_LOAD_MORE = 1;  // 加载更多
    public static final Short LOADTYPE_LOAD_NEW = 2; // 加载最新
    public static final String DEFAULT_TAG = "__all__";

    public static final String ARTICL_ES_SYNC_TOPIC_SIR = "articl_es_sync_topic_sir";

    // 文章行为分值
    public static final Integer HOT_ARTICLE_VIEW_WEIGHT = 1;
    public static final Integer HOT_ARTICLE_LIKE_WEIGHT = 3;
    public static final Integer HOT_ARTICLE_COMMENT_WEIGHT = 5;
    public static final Integer HOT_ARTICLE_COLLECTION_WEIGHT = 8;

    // 存到redis热文章前缀
    public static final String HOT_ARTICLE_FIRST_PAGE = "hot_article_first_page_";


    public static final String HOTARTICLE_SCORE_INPUT_TOPIC="hot.article.score.topic";
    public static final String HOTARTICLE_INCR_HANDLE_OUPUT_TOPIC ="hot.article.incr.handle.topic" ;

}