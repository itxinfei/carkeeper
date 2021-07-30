package com.heima.operations.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.operations.dtos.ArticleDto;
import com.heima.model.operations.pojos.Article;
import com.heima.model.common.dtos.ResponseResult;

/**
 * @Description:
 * @Version: V1.0
 */
public interface ArticleService extends IService<Article> {

    /**
     * 查询列表
     * @param dto
     * @return
     */
    public ResponseResult findPage(ArticleDto dto);

    /**
     * 新增或修改
     * @param dto
     * @return
     */
    public ResponseResult saveArticle(ArticleDto dto);

    /**
     * 删除
     * @param id
     * @return
     */
    public ResponseResult deleteById(Long id);

}
