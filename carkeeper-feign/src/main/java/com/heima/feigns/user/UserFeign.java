package com.heima.feigns.user;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.pojos.Car;
import com.heima.model.user.pojos.Receiver;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "carkeeper-user")
public interface UserFeign {

    /**
     * 查询收货方信息
     *
     * @param receiverId
     * @return
     */
    @PostMapping("/api/v1/receiver/findReceiver/{receiverId}")
    public Receiver findReceiver(@PathVariable("receiverId") Long receiverId);

    /**
     * 查询车主
     * @param carId
     * @return
     */
    @PostMapping("/api/v1/car/findCar/{carId}")
    public Car findCar(@PathVariable("carId") Long carId);

    @GetMapping("/api/v1/user/findUserRealname/{userId}")
    public ResponseResult getUserRealname(@PathVariable("userId") Long userId);
}
