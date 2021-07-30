package com.heima.operations.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.operations.pojos.BlogCategory;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Version: V1.0
 */
@Repository
public interface BlogCategoryMapper extends BaseMapper<BlogCategory> {

    @Select("SELECT id,`name` FROM tb_blog_category")
    List<String> findAll();
}
