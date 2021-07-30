package com.heima.model.user.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("tb_user")
public class AdminUser {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id; //id



    @ApiModelProperty("用户名称")
    @TableField("username")
    private String username;//用户名称

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;//密码

    @ApiModelProperty("昵称")
    @TableField("nickname")
    private String nickname;//昵称

    @ApiModelProperty("头像地址")
    @TableField("head_img_url")
    private String headImgUrl;//头像地址

    @ApiModelProperty("电话")
    @TableField("phone")
    private String phone;//电话

    @ApiModelProperty("性别")
    @TableField("sex")
    private String sex;//性别

    @ApiModelProperty("状态")
    @TableField("status")
    private String status;//状态

    @ApiModelProperty("城市编码")
    @TableField("city_code")
    private String cityCode;//城市编码

    @ApiModelProperty("来源")
    @TableField("source")
    private String source;//来源

    @ApiModelProperty("微信openid")
    @TableField("open_id")
    private String openId;//微信openid

    @ApiModelProperty("关联id")
    @TableField("union_id")
    private String unionId;//关联id

    @ApiModelProperty("app")
    @TableField("app")
    private String app;//app

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private java.util.Date createTime;//创建时间

    @ApiModelProperty("修改时间")
    @TableField("update_time")
    private java.util.Date updateTime;//修改时间
}
