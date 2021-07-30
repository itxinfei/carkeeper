package com.heima.model.operations.dtos;

import com.heima.model.operations.pojos.Blog;
import lombok.Data;

import java.util.Date;
import java.util.List;


/**
 * @Description: BlogDto
 * @Version: V1.0
 */
@Data
public class BlogDto extends Blog {

    List<Long> componentIds;
}
