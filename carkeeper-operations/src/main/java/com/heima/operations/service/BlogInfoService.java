package com.heima.operations.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.operations.dtos.BlogInfoDto;
import com.heima.model.operations.pojos.Blog;

public interface BlogInfoService {

    /**
     * 查询列表
     * @param dto
     * @return
     */
    public ResponseResult findPage(BlogInfoDto dto);
}
