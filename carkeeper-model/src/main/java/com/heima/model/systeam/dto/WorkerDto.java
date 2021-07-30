package com.heima.model.systeam.dto;

import com.heima.model.common.dtos.PageRequestDto;
import lombok.Data;

@Data
public class WorkerDto extends PageRequestDto {
    private String name;
}
