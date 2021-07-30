package com.heima.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.systeam.dto.LoginDto;
import com.heima.model.systeam.pojo.LoginPojo;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
public interface LoginMapper extends BaseMapper<LoginPojo> {
}
