package com.heima.model.order.dtos;

import lombok.Data;

import java.util.List;

@Data
public class PartAndOrderDto {
    private List<Long> partIds;
    private Long storeId;
    private Long orderId;
}
