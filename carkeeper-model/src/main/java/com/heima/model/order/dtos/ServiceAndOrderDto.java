package com.heima.model.order.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ServiceAndOrderDto {
    private List<Long> serviceIds;
    private Long storeId;
    private Long orderId;
}
