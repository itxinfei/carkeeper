package com.heima.model.systeam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("carkeeper_table_systeam_user")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String des;
    private String phone;
    private Double salary;
    private Integer uid;
    private Integer IsMember;
    private Integer IsDel;

}
