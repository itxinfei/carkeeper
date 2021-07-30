package com.heima.search.service.impl;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.es.pojo.CatalogSearchDto;
import com.heima.search.service.CatalogSearchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CatalogSearchServiceImpl implements CatalogSearchService {

    @Autowired
    RestHighLevelClient client;
    /**
     * ES服务分页搜索
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult search(CatalogSearchDto dto) {
        //1.参数检查
        if (dto == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        //分页检查
        dto.checkParam();
        try {
            //2.构建查询条件
            SearchSourceBuilder build = new SearchSourceBuilder();
            //bool查询
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
            //title
            if (null != dto.getName()) {
                MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("name", dto.getName());

                boolQuery.must(matchQuery);
            }
            if (null != dto.getTypeSonName()) {
                MatchQueryBuilder matchQuery1 = QueryBuilders.matchQuery("sonName", dto.getTypeSonName());
                boolQuery.must(matchQuery1);
            }
            if (null != dto.getTypeSonCode()) {
                MatchQueryBuilder matchQuery2 = QueryBuilders.matchQuery("sonCode", dto.getTypeSonCode());
                boolQuery.must(matchQuery2);
            }
            if (null != dto.getTypeParentName()) {
                MatchQueryBuilder matchQuery3 = QueryBuilders.matchQuery("parentName", dto.getTypeParentName());
                boolQuery.must(matchQuery3);
            }


            //分页
            build.size(dto.getSize());
            //from() 起始页 currentPage
            if (dto.getPage()<1){
                build.from(0);// TODO 头条里的是下拉刷新，是伪分页
            }
            build.from(dto.getPage()-1);

            // 高亮 三要素
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("name");
            highlightBuilder.preTags("<font style=\"color:red\">");
            highlightBuilder.postTags("</font>");
            build.highlighter(highlightBuilder);
            build.query(boolQuery);
            //3.执行查询
            SearchRequest request = new SearchRequest("table");

            build.query(boolQuery);
            request.source(build);

            SearchResponse response = client.search(request, RequestOptions.DEFAULT);

            //4.封装结果
            SearchHits hits = response.getHits();
            long total = hits.getTotalHits().value;


            //返回
            List<Map> resultList = new ArrayList<>();

            SearchHit[] searchHits = hits.getHits();
            for (SearchHit searchHit : searchHits) {

                //原始数据
                Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();

                //高亮结果
                HighlightField highlightField = searchHit.getHighlightFields().get("name");

                //保留原始不带高亮的标题
                sourceAsMap.put("o_name",sourceAsMap.get("name"));

                String htitle = StringUtils.join(highlightField.fragments());
                if (StringUtils.isNotBlank(htitle)) {
                    sourceAsMap.put("name",htitle);
                }
                resultList.add(sourceAsMap);
            }
            //5.返回数据
            return ResponseResult.okResult(resultList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
    }
}
