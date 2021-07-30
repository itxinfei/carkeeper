package com.heima.model.shop.dtos;


import lombok.Data;

@Data
public class StoreAuthDto {
    /**
     * 主体编码
     */
    private Long code;

    /**
     * 主体状态
     */
    public Short flag;
}
