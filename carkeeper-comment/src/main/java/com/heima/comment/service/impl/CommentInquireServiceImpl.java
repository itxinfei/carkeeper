package com.heima.comment.service.impl;

import com.heima.comment.pojos.TbServeComment;
import com.heima.comment.service.CommentInquireService;
import com.heima.model.common.comment.dtos.CommentCommodityDto;
import com.heima.model.common.comment.dtos.CommentServeDto;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Slf4j
public class CommentInquireServiceImpl implements CommentInquireService {

    @Autowired
    MongoTemplate mongoTemplate;
    /**
     * 按条件查询评价内容
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult findAll(CommentServeDto dto) throws ParseException {

        //检查参数
        if (dto == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }

        if (dto.getSize() == null){
            dto.setSize(5);
        }
        if (dto.getPage() == null){
            dto.setPage(1);
        }

        //查询服务评论
        Criteria type = Criteria.where("type").is(0);

      //订单编号查询
        Criteria orderNumber = Criteria.where("type").is(0);
       if (dto.getOrderNumber()!=null && dto.getOrderNumber().length()>0){
           Pattern pattern = Pattern.compile("^.*" +dto.getOrderNumber() + ".*$",Pattern.CASE_INSENSITIVE);
           orderNumber = Criteria.where("orderNumber").is(dto.getOrderNumber());
        }

        //客户名称查询
        Criteria authorName = Criteria.where("type").is(0);
        if (dto.getAuthorName() != null){
            Pattern pattern = Pattern.compile("^.*" +dto.getAuthorName() + ".*$",Pattern.CASE_INSENSITIVE);
            authorName = Criteria.where("authorName").is(pattern);
        }
        //评分查询
        Criteria synthesize = Criteria.where("type").is(0);
        if (dto.getGrade() != null){
            synthesize = Criteria.where("synthesize").is(dto.getGrade());
        }

        //服务项目查询
        Criteria shop = Criteria.where("type").is(0);
        if (dto.getShop() != null){
             shop = Criteria.where("shop").is(dto.getShop());
        }

        //服务分类
        Criteria classify = Criteria.where("type").is(0);
        if (dto.getClassify() != null){
            classify = Criteria.where("classify").is(dto.getClassify());
        }
        //时间查询
        Criteria orderTime = Criteria.where("type").is(0);
        if (dto.getBeginPubDate() != null && dto.getEndPubDate() != null){
            String little = dto.getBeginPubDate().toString();
            String big = dto.getEndPubDate().toString();

            String par = "yyyy-MM-dd hh:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(par);
            Date parselittle = sdf.parse(little);
            Date parsebig = sdf.parse(big);
            System.out.println("parselittle: "+parselittle);
            System.out.println("parsebig "+parsebig);
            orderTime = Criteria.where("orderTime").lte(parsebig).gte(parselittle);
//            orderTime = Criteria.where("orderTime").lte(dto.getEndPubDate()).gte(dto.getBeginPubDate());
        }

        //查询
        Query query = new Query().addCriteria(new Criteria().andOperator(type,authorName,synthesize,shop,orderTime,orderNumber,classify));
        List<TbServeComment> tbComments = mongoTemplate.find(query, TbServeComment.class);
        for (TbServeComment tbComment : tbComments) {
            System.out.println("在那边："+tbComment);
        }
        Integer size = tbComments.size();
        Integer number = (dto.getPage() - 1) * dto.getSize();
        query.with(Sort.by(Sort.Direction.DESC,"orderTime")).skip(number).limit(dto.getSize());
        List<TbServeComment> tbServeComments = mongoTemplate.find(query, TbServeComment.class);
        ResponseResult result = new PageResponseResult(dto.getPage(), dto.getSize(), size, tbServeComments);
        return ResponseResult.okResult(result);
    }

    /**
     * 按条件查询商品评价内容
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult findCommodityAll(CommentCommodityDto dto) throws ParseException {

        //检查参数
        if (dto == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        if (dto.getSize() == null){
            dto.setSize(5);
        }
        if (dto.getPage() == null){
            dto.setPage(1);
        }

        //创造条件
        Criteria type = Criteria.where("type").is(0);

        //订单编号查询
        Criteria orderNumber = Criteria.where("type").is(0);
        if (dto.getOrderNumber()!=null && dto.getOrderNumber().length()>0){
            Pattern pattern = Pattern.compile("^.*" +dto.getOrderNumber() + ".*$",Pattern.CASE_INSENSITIVE);
            orderNumber = Criteria.where("orderNumber").is(dto.getOrderNumber());
        }


        //客户名称查询
        Criteria authorName = Criteria.where("type").is(0);
        if (dto.getAuthorName() != null){
            Pattern pattern = Pattern.compile("^.*" +dto.getAuthorName() + ".*$",Pattern.CASE_INSENSITIVE);
            authorName = Criteria.where("authorName").is(pattern);
        }

        //评分查询
        Criteria synthesize = Criteria.where("type").is(0);
        if (dto.getGrade() != null){
            synthesize = Criteria.where("synthesize").is(dto.getGrade());
        }

        //配件商
        Criteria shopkeeper = Criteria.where("type").is(0);
        if (dto.getShopkeeper() != null){
            shopkeeper = Criteria.where("shop").is(dto.getShopkeeper());
        }
        //时间查询
        Criteria orderTime = Criteria.where("type").is(0);
        if (dto.getBeginPubDate() != null && dto.getEndPubDate() != null){
            String little = dto.getBeginPubDate().toString();
            String big = dto.getEndPubDate().toString();

            String par = "yyyy-MM-dd hh:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(par);
            Date parselittle = sdf.parse(little);
            Date parsebig = sdf.parse(big);
            System.out.println("parselittle: "+parselittle);
            System.out.println("parsebig "+parsebig);
            orderTime = Criteria.where("orderTime").lte(parsebig).gte(parselittle);
        }
        //查询
        Query query = new Query().addCriteria(new Criteria().andOperator(type,authorName,synthesize,shopkeeper,orderTime,orderNumber));
        List<TbServeComment> tbComments = mongoTemplate.find(query,TbServeComment.class);
        Integer size = tbComments.size();
        Integer number = (dto.getPage() - 1) * dto.getSize();
        query.with(Sort.by(Sort.Direction.DESC,"orderTime")).skip(number).limit(dto.getSize());
        List<TbServeComment> tbServeComments = mongoTemplate.find(query, TbServeComment.class);
        ResponseResult result = new PageResponseResult(dto.getPage(), dto.getSize(), size, tbServeComments);
        return ResponseResult.okResult(result);
    }

    /**
     * 服务评论 的查询
     * @param dto
     * @return
     */
    @Override
    public ResponseResult Inquire(String dto) {
        if (dto == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        //查询
        TbServeComment tbComment = mongoTemplate.findById(dto, TbServeComment.class);
        return ResponseResult.okResult(tbComment);
    }

    /**
     * 服务评论 的删除
     * @param dto
     * @return
     */
    @Override
    public ResponseResult delete(String dto) {
        if (dto == null ){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }

        mongoTemplate.remove(Query.query(Criteria.where("id").is(dto))
                ,TbServeComment.class);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    /**
     * 配件评论 的查询
     * @param dto
     * @return
     */
    @Override
    public ResponseResult Inquirecommodity(String dto) {
        if (dto == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        //查询
        TbServeComment tbComment = mongoTemplate.findById(dto, TbServeComment.class);
        return ResponseResult.okResult(tbComment);
    }
    /**
     * 配件评论 的删除
     * @param dto
     * @return
     */
    @Override
    public ResponseResult deletecommodity(String dto) {
        if (dto == null ){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }

        mongoTemplate.remove(Query.query(Criteria.where("id").is(dto))
                ,TbServeComment.class);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

}
