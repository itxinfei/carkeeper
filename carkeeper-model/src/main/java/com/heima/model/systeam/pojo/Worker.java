package com.heima.model.systeam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.heima.model.common.dtos.PageRequestDto;
import lombok.Data;

@Data
@TableName("carkeeper_table_systeam_worker")
public class Worker {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String des;
    private String deparment;
    private String phone;
}
