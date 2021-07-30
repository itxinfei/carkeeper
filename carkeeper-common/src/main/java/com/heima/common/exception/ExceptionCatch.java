package com.heima.common.exception;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice  //异常捕捉拦截
@Slf4j
@Configuration
public class ExceptionCatch {

    //捕获Exception此类异常
    @ExceptionHandler(Exception.class)
    public ResponseResult exception(Exception ex) {
        ex.printStackTrace();
        //记录日志
        log.error("catch exception:{}",ex.getMessage());
        //返回通用异常
        return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
    }

    //可预知异常
   @ExceptionHandler(CustomException.class)
    public ResponseResult exception(CustomException ex){
        ex.printStackTrace();
        log.error("CustomException ex:{}",ex);
        return ResponseResult.errorResult(ex.getAppHttpCodeEnum());
   }

}
