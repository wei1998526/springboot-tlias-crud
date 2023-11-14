package com.wei.exception;

import com.wei.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */

@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(Exception.class)  //捕获所有异常
    public Result ex (Exception e){
        e.printStackTrace();
        return Result.error("操作失败，请联系管理员");
    }
}
