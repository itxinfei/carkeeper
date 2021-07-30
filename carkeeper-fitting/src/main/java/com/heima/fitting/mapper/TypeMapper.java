package com.heima.fitting.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.fitting.pojos.Type;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TypeMapper extends BaseMapper<Type> {

    //查询所有配件类型名称
    @Select("select type from fitting_type GROUP BY type")
    List<String> findAllType();
}