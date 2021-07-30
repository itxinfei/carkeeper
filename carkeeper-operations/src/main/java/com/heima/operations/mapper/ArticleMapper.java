package com.heima.operations.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.operations.dtos.ArticleDto;
import com.heima.model.operations.pojos.Article;
import com.heima.model.operations.vos.ArticleVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Version: V1.0
 */
@Repository
public interface ArticleMapper extends BaseMapper<Article> {
    /**
     * 查询列表
     * @param dto
     * @return
     */
    List<ArticleVo> findPage(@Param("dto") ArticleDto dto);

    /**
     * 查询总记录数
     * @param dto
     * @return
     */
    int findCount(@Param("dto") ArticleDto dto);

}
