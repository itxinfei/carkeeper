package com.heima.operations.service;

import com.heima.model.common.dtos.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    /**
     * 上传图片
     * @param multipartFile
     * @return
     */
    public ResponseResult uploadPicture(MultipartFile multipartFile);

}
