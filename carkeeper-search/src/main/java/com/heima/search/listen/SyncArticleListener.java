package com.heima.search.listen;

import com.alibaba.fastjson.JSON;
import com.heima.model.common.constants.ArticleConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @Description:
 * @Version: V1.0
 */
@Component
@Slf4j
public class SyncArticleListener {


    @Autowired
    RestHighLevelClient client;


    @KafkaListener(topics = ArticleConstants.ARTICL_ES_SYNC_TOPIC_SIR)
    public void receiveMessage(String message) {

        if (StringUtils.isNotBlank(message)) {

            log.info("SyncArticleListener receiveMessage:{}", message);

            Map map = JSON.parseObject(message, Map.class);

            IndexRequest indexRequest = new IndexRequest("table");
            indexRequest.id(map.get("id").toString());
            indexRequest.source(message, XContentType.JSON);

            try {
                client.index(indexRequest, RequestOptions.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("sync es error:{}", e);

            }
            log.info("sync es success");
        }

    }
}
