package com.heima.model.systeam.dto;

import com.heima.model.common.dtos.PageRequestDto;
import com.heima.model.common.dtos.PageResponseResult;
import lombok.Data;

@Data
public class SyDeparmentDto extends PageRequestDto {
    private String name;
}
