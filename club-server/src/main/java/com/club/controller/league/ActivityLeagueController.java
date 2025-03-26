package com.club.controller.league;

import com.club.result.PageResult;
import com.club.result.Result;
import com.club.service.ActivityLeagueService;
import com.club.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Api(tags = "活动相关接口")
@RestController
@RequestMapping("/admin/league/activity")
@Slf4j
public class ActivityLeagueController {
    @Autowired
    private ActivityLeagueService activityLeagueService;

    @Autowired
    private AliOssUtil aliOssUtil;
    /***
     * 校团委端获取所有的活动模板
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("校团委端获取所有的活动模板")
    @GetMapping("/get_template")
    public Result<PageResult> getAllTemplate( Integer pageNum,  Integer pageSize){
        log.info("校团委端获取所有的文件模板:{},{}",pageNum,pageSize);
        PageResult pageResult = activityLeagueService.getAllTemplate(pageNum,pageSize);
        return Result.success(pageResult);
    }

    /***
     * 校团委端添加活动模板
     * @param templateName
     * @param templateFile
     * @param templateType
     * @return
     */
    @PostMapping("/add_template")
    @ApiOperation("校团委端添加活动模板")
    public Result<String>uploadTemplate(@RequestParam(value = "templateName") String templateName,
                                        @RequestParam(value = "templateFile") String templateFile,
                                        @RequestParam(value = "templateType") Integer templateType){
        log.info("校团委端添加活动模板:{},{},{}",templateName,templateFile,templateType);
        //将url和其它参数存储到文件中
        activityLeagueService.addTemplate(templateName,templateFile,templateType);
        return Result.success("添加成功，文件路径为:"+templateFile);
    }

    /***
     * 校团委端删除活动模板
     * @param templateId
     * @return
     */
    @DeleteMapping("/delete_template")
    @ApiOperation("校团委端删除活动模板")
    public Result<String>deleteTemplate(Integer templateId){
        log.info("校团委端删除活动模板:{}",templateId);
        activityLeagueService.deleteTemplate(templateId);
        return Result.success("删除模板成功");
    }
}
