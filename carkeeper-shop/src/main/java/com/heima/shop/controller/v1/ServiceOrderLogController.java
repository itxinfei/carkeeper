package com.heima.shop.controller.v1;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.shop.dtos.ServiceAndOrderLogDto;
import com.heima.shop.mapper.ServiceOrderLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/serviceLog")
@RestController
public class ServiceOrderLogController {

    @Autowired
    ServiceOrderLogMapper serviceOrderLogMapper;

    /**
     * 插入操作员操作记录
     */
    @PostMapping("/insertServiceLong")
    public ResponseResult insertServiceLong(@RequestBody ServiceAndOrderLogDto dto) {
        int result = serviceOrderLogMapper.insert(dto);
        if (result==1){
            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
    }
}
