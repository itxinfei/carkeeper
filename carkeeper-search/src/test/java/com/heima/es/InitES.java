package com.heima.es;


import com.alibaba.fastjson.JSON;
import com.heima.model.es.pojo.CatalogVo;
import com.heima.model.shop.dtos.CatalogDto;
import com.heima.search.SearchApplication;
import com.heima.search.mapper.CatalogMapper;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@SpringBootTest(classes = SearchApplication.class)
@RunWith(SpringRunner.class)
public class InitES {

    @Autowired
    RestHighLevelClient client;

    @Autowired
    CatalogMapper catalogMapper;

    @Test
    public void initMysqlToEs() throws IOException {
        int a = 0;
        int size = 200;
        while (true){

            List<CatalogVo> catalog = catalogMapper.findCatalog(new CatalogDto(), a, size);
            if (catalog.size() > 0){

                BulkRequest bulk = new BulkRequest("table");

                for (CatalogVo catalogVo : catalog) {
                    bulk.add(new IndexRequest().id(catalogVo.getId().toString()).source(JSON.toJSONString(catalogVo), XContentType.JSON));
                }
                client.bulk(bulk, RequestOptions.DEFAULT);
                a+=size;
            }else{
                break;
            }
        }

}
}
