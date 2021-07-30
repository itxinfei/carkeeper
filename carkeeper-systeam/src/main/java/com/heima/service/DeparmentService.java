package com.heima.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.systeam.dto.SyDeparmentDto;
import com.heima.model.systeam.pojo.SyDeparment;
import org.springframework.stereotype.Service;


public interface DeparmentService extends IService<SyDeparment> {
    PageResponseResult find(SyDeparmentDto syDeparmentDto);

    ResponseResult insert(SyDeparment syDeparment);

    ResponseResult updated(SyDeparment syDeparment);

    ResponseResult del(Integer id);
}
