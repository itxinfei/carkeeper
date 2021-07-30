package com.heima.apis.comment;

import com.heima.model.common.comment.dtos.CommentCommodityDto;
import com.heima.model.common.comment.dtos.CommentServeDto;
import com.heima.model.common.dtos.ResponseResult;
import io.swagger.annotations.ApiOperation;

import java.text.ParseException;

public interface CommentControllerApi {

    /**
     * 按条件查询评价内容
     * @param dto
     * @return
     */
    @ApiOperation("按条件查询评价内容")
    public ResponseResult findAll(CommentServeDto dto) throws ParseException;

    /**
     * 按条件查询商品评价内容
     * @param dto
     * @return
     */
    public ResponseResult findCommodityAll(CommentCommodityDto dto) throws ParseException;

    /**
     * 配件评论/ 的查询
     * @param dto
     * @return
     */
    public ResponseResult Inquirecommodity(String dto);

    /**
     * 服务评论 的查询
     * @param dto
     * @return
     */
    public ResponseResult Inquire(String dto);

    /**
     * 服务评论 的删除
     * @param dto
     * @return
     */
    public ResponseResult delete(String dto);

    /**
     * 配件评论 的删除
     * @param dto
     * @return
     */
    public ResponseResult deletecommodity(String dto);
}
