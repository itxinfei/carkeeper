package com.heima.user.controller.v1;

import com.heima.apis.user.ReceiverControllerApi;
import com.heima.model.user.pojos.Receiver;
import com.heima.user.service.ReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description:
 * @Version: V1.0
 */
@RestController
@RequestMapping("/api/v1/receiver")
public class ReceiverController implements ReceiverControllerApi {

    @Autowired
    ReceiverService receiverService;

    /**
     * 查询收货方信息
     *
     * @param receiverId
     * @return
     */
    @PostMapping("/findReceiver/{receiverId}")
    @Override
    public Receiver findReceiver(@PathVariable("receiverId") Long receiverId) {
        return receiverService.getById(receiverId);
    }

}
