package com.wei.controller;

import com.aliyuncs.exceptions.ClientException;
import com.wei.pojo.Result;
import com.wei.utils.AliOssUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    AliOssUtils aliOssUtils = new AliOssUtils();

    //文件上传-本地存储
//    @PostMapping("/upload")
//    public Result reload(String username, Integer age, MultipartFile image) throws IOException {
//        log.info("文件上传,{},{},{}", username, age, image);
//        //获取原始文件名
//        String originalFilename = image.getOriginalFilename();
//        log.info("原文件名：{}", originalFilename);
//        //根据最后一个.截取
//        int index = 0;
//        if (originalFilename != null) {
//            index = originalFilename.lastIndexOf(".");
//        }
//        String extname = originalFilename.substring(index);
//        //根据uuid生成新名称
//        String newFileName = UUID.randomUUID().toString() + extname;
//        log.info("新的文件名称：{}", newFileName);
//        //存储文件到本地
//        image.transferTo(new File("E:\\javaEE\\JavaProject\\image\\" + newFileName));
//        return Result.success();
//    }


    //文件上传-阿里云存储
    @PostMapping("/upload")
    public Result reload(MultipartFile image) throws ClientException, IOException {
        log.info("文件上传，文件名：{}", image.getOriginalFilename());
        // 调用阿里云oss工具类上传文件
        String url = aliOssUtils.upload(image);
        log.info("文件上传完成，url：{}",url);
        return Result.success(url);
    }
}
