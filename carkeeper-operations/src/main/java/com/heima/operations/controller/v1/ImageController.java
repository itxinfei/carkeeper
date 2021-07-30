package com.heima.operations.controller.v1;

import com.heima.apis.operations.ImageControllerApi;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.operations.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
public class ImageController implements ImageControllerApi {

    @Autowired
    private ImageService imageService;

    /**
     * 上传图片
     *
     * @param multipartFile
     * @return
     */
    @Override
    @PostMapping("/upload")
    public ResponseResult uploadPicture(@RequestParam("file") MultipartFile multipartFile) {
        return imageService.uploadPicture(multipartFile);
    }

}
