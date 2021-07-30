package com.heima.model.systeam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("carkeeper_table_systeam_deparment")
public class SyDeparment {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
     @TableField("name")
    private String name;

    private String address;

    private String des;

    private String leader;

    private String phone;
}
