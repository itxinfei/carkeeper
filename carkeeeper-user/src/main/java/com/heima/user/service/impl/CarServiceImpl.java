package com.heima.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.user.pojos.Car;
import com.heima.user.mapper.CarMapper;
import com.heima.user.service.CarService;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Version: V1.0
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService {
}
