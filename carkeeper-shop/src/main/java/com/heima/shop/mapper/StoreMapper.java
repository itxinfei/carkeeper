package com.heima.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.shop.pojos.Store;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @Description:
 * @Version: V1.0
 */
public interface StoreMapper extends BaseMapper<Store> {
   @Update("update tb_store set flag = #{flag} where code = #{code} ")
    void updateByCode(@Param("code") Long code, @Param("flag") short flag);
}
