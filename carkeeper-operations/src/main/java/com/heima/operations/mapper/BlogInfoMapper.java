package com.heima.operations.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.operations.dtos.BlogDto;
import com.heima.model.operations.dtos.BlogInfoDto;
import com.heima.model.operations.pojos.Blog;
import com.heima.model.operations.vos.BlogVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Version: V1.0
 */
@Repository
public interface BlogInfoMapper  {

    /**
     * 查询列表
     * @param dto
     * @return
     */
    List<BlogVo> findPage(@Param("dto") BlogInfoDto dto);

    /**
     * 查询总记录数
     * @param dto
     * @return
     */
    int findCount(@Param("dto") BlogInfoDto dto);
}

