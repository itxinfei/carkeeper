package com.heima.shop.controller.v1;

import com.heima.apis.shop.ServiceControllerApi;
import com.heima.model.order.dtos.ServiceAndOrderDto;
import com.heima.shop.service.ServiceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * @Description:
 * @Version: V1.0
 */
@RestController
@RequestMapping("/api/v1/service")
public class ServiceController implements ServiceControllerApi {

    @Autowired
    ServiceInfo serviceInfo;

    /**
     * 查询服务集合
     *
     * @param serviceAndOrderDto
     * @return
     */
    @Override
    @PostMapping("/findServiceAndStore")
    public Map findServiceAndStore(@RequestBody ServiceAndOrderDto serviceAndOrderDto) {
        return serviceInfo.findServiceAndStore(serviceAndOrderDto);
    }
}
