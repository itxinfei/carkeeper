package com.heima.comment.vos;

import com.heima.comment.pojos.TbServeComment;
import lombok.Data;

@Data
public class TbCommentVo extends TbServeComment {

    /**
     * 0：点赞
     * 1：取消点赞
     */
    private Short operation;
}
