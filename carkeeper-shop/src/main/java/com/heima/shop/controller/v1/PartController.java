package com.heima.shop.controller.v1;

import com.heima.apis.shop.PartControllerApi;
import com.heima.model.order.dtos.PartAndOrderDto;
import com.heima.shop.service.PartService;
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
@RequestMapping("/api/v1/part")
public class PartController implements PartControllerApi {

    @Autowired
    PartService partService;

    /**
     * 查询配件集合
     * @param partAndOrderDto
     * @return
     */
    @PostMapping("/findPartsAndStore")
    public Map findPartsAndStore(@RequestBody PartAndOrderDto partAndOrderDto) {
     return partService.findPartsAndStore(partAndOrderDto);
    }
}
