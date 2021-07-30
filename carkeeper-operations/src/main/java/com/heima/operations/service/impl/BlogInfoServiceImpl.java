package com.heima.operations.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.operations.dtos.BlogInfoDto;
import com.heima.model.operations.pojos.Blog;
import com.heima.model.operations.vos.BlogVo;
import com.heima.operations.mapper.BlogInfoMapper;
import com.heima.operations.service.BlogInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogInfoServiceImpl implements BlogInfoService {

    @Value("${file.oss.web-site}")
    String webSite;

    @Autowired
    private BlogInfoMapper blogInfoMapper;

    /**
     * 查询列表
     * @param dto
     * @return
     */
    @Override
    public ResponseResult findPage(BlogInfoDto dto) {
        //1 参数检查
        if (dto == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        // 分页检查
        dto.checkParam();

        //模糊查询
        if (StringUtils.isNotBlank(dto.getTitle())){
            dto.setTitle("%" + dto.getTitle() + "%");
        }
        //处理分页起始索引
        Integer page = dto.getPage();
        int from = (page - 1) * dto.getSize();
        dto.setPage(from);
        //2 执行查询
        List<BlogVo> blogVos = blogInfoMapper.findPage(dto);
        //排序
        blogVos = blogVos.stream().sorted(Comparator.comparing(BlogVo::getSort)).collect(Collectors.toList());
        int count = blogInfoMapper.findCount(dto);
        //返回结果
        PageResponseResult result = new PageResponseResult(page, dto.getSize(), count, blogVos);
        result.setHost(webSite);
        return result;
    }
}
