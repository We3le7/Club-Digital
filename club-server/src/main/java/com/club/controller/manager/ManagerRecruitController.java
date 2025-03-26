package com.club.controller.manager;

import com.club.dto.ApplyPageDTO;
import com.club.dto.InterviewDTO;
import com.club.dto.PublishRecruitDTO;
import com.club.dto.ShenpiDTO;
import com.club.result.PageResult;
import com.club.result.Result;
import com.club.service.RecruitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/manager/recruit")
@Slf4j
@Api(tags = "社团管理端-招新")
public class ManagerRecruitController {
    @Autowired
    private RecruitService recruitService;

    /**
     * 发布招新
     * @param publishRecruitDTO
     * @return
     */
 
    @PostMapping("/publishRecruit")
    @ApiOperation(value = "发布招新")
    public Result<String> publishRecruit(@RequestBody PublishRecruitDTO publishRecruitDTO) {
        log.info("发布招新:{}", publishRecruitDTO);
        recruitService.publishRecruit(publishRecruitDTO);
        return Result.success();
    }

    /**
     * 查看所有招新
     * @param applyPageDTO
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查看所有申请")
    public Result<PageResult> getAllApplication(ApplyPageDTO applyPageDTO) {
        log.info("查看所有申请:{}", applyPageDTO);
        PageResult pageResult = recruitService.getAllApplication(applyPageDTO);
        return Result.success(pageResult);
    }

    /**
     * 批准加入
     */
    @PostMapping("/approve")
    @ApiOperation(value = "批准加入")
    public Result<String> approve(@RequestBody ShenpiDTO apply) {
        log.info("批准加入:{}", apply);
        recruitService.approve(apply.getApplyId());
        return Result.success();
    }

    /**
     * 拒绝加入
     */
    @PostMapping("/reject")
    @ApiOperation(value = "拒绝加入")
    public Result<String> reject(@RequestBody ShenpiDTO apply) {
        log.info("拒绝入:{}", apply);
        recruitService.reject(apply.getApplyId());
        return Result.success();
    }

    /**
     * 面试
     * @param
     * @return
     */
    @PostMapping(value = "/interview")
    @ApiOperation(value = "面试")
    public Result<String> interview(@RequestBody InterviewDTO interviewDTO){
        recruitService.interview(interviewDTO);
        return Result.success();
    }

}
