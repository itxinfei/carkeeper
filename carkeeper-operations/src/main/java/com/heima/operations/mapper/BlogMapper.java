package com.heima.operations.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.operations.pojos.Blog;
import com.heima.model.operations.pojos.BlogComponent;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Version: V1.0
 */
@Repository
public interface BlogMapper extends BaseMapper<Blog> {
    @Insert("insert into tb_blog_component(blog_id,component_id) values(#{blogId},#{componentId})")
    void addComponent(@Param("blogId") Long blogId, @Param("componentId") Long componentId);

    @Select("select id,blog_id,component_id from tb_blog_component where blog_id = #{blogId} and component_id = #{componentId}")
    BlogComponent findBlogComponent(@Param("blogId") Long blogId, @Param("componentId") Long componentId);

    @Delete("delete from tb_blog_component where blog_id = #{blogId}")
    void delComponent(@Param("blogId") Long blogId);
}
