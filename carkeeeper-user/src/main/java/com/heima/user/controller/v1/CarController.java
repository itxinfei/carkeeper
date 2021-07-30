package com.heima.user.controller.v1;

import com.heima.apis.user.CarControllerApi;
import com.heima.model.user.pojos.Car;
import com.heima.user.service.CarService;
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
@RequestMapping("/api/v1/car")
public class CarController implements CarControllerApi {

    @Autowired
    CarService carService;

    /**
     * 查询车主
     * @param carId
     * @return
     */
    @Override
    @PostMapping("/findCar/{carId}")
    public Car findCar(@PathVariable("carId") Long carId) {
        return carService.getById(carId);
    }

}
