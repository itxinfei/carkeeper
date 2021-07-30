package com.heima.feigns.fitting;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.fitting.dtos.DirectoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("fitting")
public interface FittingFeign {

    /**
     * 查询列表
     * @param dto
     * @return
     */
    @PostMapping("/api/v1/directory/list")
    public ResponseResult findPage(@RequestBody DirectoryDto dto);
}
