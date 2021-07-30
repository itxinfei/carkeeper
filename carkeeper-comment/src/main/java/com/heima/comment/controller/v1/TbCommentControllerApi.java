package com.heima.comment.controller.v1;

import com.heima.apis.comment.CommentControllerApi;
import com.heima.comment.service.CommentInquireService;
import com.heima.model.common.comment.dtos.CommentCommodityDto;
import com.heima.model.common.comment.dtos.CommentServeDto;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/v1/comment")
public class TbCommentControllerApi implements CommentControllerApi {

    @Autowired
    CommentInquireService commentInquireService;
    /**
     * 查询配件信息
     *
     * @param dto
     * @return
     */
    @PostMapping("/findAll")
    @Override
    public ResponseResult findAll(@RequestBody CommentServeDto dto) throws ParseException {
        return commentInquireService.findAll(dto);
    }

    /**
     * 查询配件信息
     *
     * @param dto
     * @return
     */
    @PostMapping("/findCommodityAll")
    @Override
    public ResponseResult findCommodityAll(@RequestBody CommentCommodityDto dto) throws ParseException {
        return commentInquireService.findCommodityAll(dto);
    }

    /**
     * 配件评论/服务评论 的查询
     *
     * @param dto
     * @return
     */
    @GetMapping("/Inquirecommodity/{id}")
    @Override
    public ResponseResult Inquirecommodity(@PathVariable("id")String dto) {
        return commentInquireService.Inquirecommodity(dto);
    }

    /**
     * 配件评论/服务评论查询
     *
     * @param dto
     * @return
     */
    @GetMapping("/Inquire/{id}")
    @Override
    public ResponseResult Inquire(@PathVariable("id") String dto) {
        return commentInquireService.Inquire(dto);
    }

    /**
     * 配件评论/服务评论 的删除
     *
     * @param dto
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @Override
    public ResponseResult delete(@PathVariable("id") String dto) {
        return commentInquireService.delete(dto);
    }

    /**
     * 配件评论/服务评论 的删除
     *
     * @param dto
     * @return
     */
    @DeleteMapping("/deletecommodity/{id}")
    @Override
    public ResponseResult deletecommodity(@PathVariable("id") String dto) {
        return commentInquireService.deletecommodity(dto);
    }
}
