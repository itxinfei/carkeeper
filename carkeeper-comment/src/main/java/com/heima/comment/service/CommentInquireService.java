package com.heima.comment.service;

import com.heima.model.common.comment.dtos.CommentCommodityDto;
import com.heima.model.common.comment.dtos.CommentServeDto;
import com.heima.model.common.dtos.ResponseResult;

import java.text.ParseException;

public interface CommentInquireService {
    /**
     * 按条件查询评价内容
     * @param dto
     * @return
     */
    public ResponseResult findAll(CommentServeDto dto) throws ParseException;

    /**
     * 按条件查询商品评价内容
     * @param dto
     * @return
     */
    public ResponseResult findCommodityAll(CommentCommodityDto dto) throws ParseException;

    /**
     * 配件评论/服务评论 的查询
     * @param dto
     * @return
     */
    public ResponseResult Inquirecommodity(String dto);


    /**
     * 配件评论/服务评论 的查询
     * @param dto
     * @return
     */
    public ResponseResult Inquire(String dto);

    /**
     * 配件评论/服务评论 的删除
     * @param dto
     * @return
     */
    public ResponseResult delete(String dto);

    /**
     * 配件评论/服务评论 的删除
     * @param dto
     * @return
     */
    public ResponseResult deletecommodity(String dto);
}
