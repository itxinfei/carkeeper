package com.heima.model.operations.vos;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class ArticleVo {

    @TableField("name")
    private String name;//推文名称

    @TableField("category")
    private String category;//所属分类（二级分类）

}
