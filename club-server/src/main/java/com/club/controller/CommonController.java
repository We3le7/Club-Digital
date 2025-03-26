package com.club.controller;

import com.club.constant.MessageConstant;
import com.club.result.Result;
import com.club.service.ConfigService;
import com.club.utils.AliOssUtil;
import com.club.vo.FileVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.util.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonController {
    @Autowired
    private AliOssUtil aliOssUtil;
    @Autowired
    private ConfigService configService;

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("照片上传")
    public Result<FileVO> upload(MultipartFile file) {
        log.info("照片上传{}", file);
        FileVO fileVO = new FileVO();
        try {
            //原始文件名
            String originalFilename = file.getOriginalFilename();
            //截取原始文件后缀
            String extension = "$" + originalFilename;
            //构造新文件名称
            String objectName = UUID.randomUUID().toString() + extension;
            //文件请求路径
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            fileVO.setUrl(filePath);
            fileVO.setFileName(originalFilename);

            return Result.success(fileVO);
        } catch (IOException e) {
            log.error("文件上传失败,{}", e);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }

    /**
     * 配置表接口
     *
     * @param name
     * @return
     */
    @GetMapping("/config")
    @ApiOperation("配置表接口")
    public Result<String> config(String name) {
        log.info("配置表接口:{}", name);
        String config = configService.getConfig(name);
        log.info("配置表接口返回:{}", config);
        return Result.success(config);
    }
    @DeleteMapping("/delete")
    @ApiOperation("删除")
    public Result deleteUrl(String url){
        log.info("删除:{}",url);
        return Result.success();
    }
}
