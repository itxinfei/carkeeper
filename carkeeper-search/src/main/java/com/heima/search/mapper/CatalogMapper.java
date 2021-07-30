package com.heima.search.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.es.pojo.CatalogVo;
import com.heima.model.shop.dtos.CatalogDto;
import com.heima.model.shop.pojos.Catalog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Version: V1.0
 */
public interface CatalogMapper extends BaseMapper<Catalog> {


    public List<CatalogVo> findCatalog(@Param("dto") CatalogDto dto, @Param("start") int start, @Param("count") int count);
}
