package com.club.controller.manager;

import com.club.dto.AuditResultDTO;
import com.club.entity.StudentInfo;
import com.club.result.PageResult;
import com.club.result.Result;
import com.club.service.ExaminationService;
import com.club.service.MemberManagementService;
import com.club.vo.AllAuditResultVO;
import com.club.vo.ClubQuitAccessVO;
import com.club.vo.StudentInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/user/manager/memberManagement")

@Api(tags = "社团管理端-成员管理")
public class MemberAdminController {
    @Autowired
    private MemberManagementService memberManagementService;

    /**
     * 查看所有社团成员
     */
    @GetMapping("allMember")
    @ApiOperation("查看所有社团成员信息")
    public Result<PageResult> getAllMember(Integer clubId, Integer pageNum, Integer pageSize) {
        PageResult allMember = memberManagementService.getAllMember(clubId, pageNum, pageSize);
        return Result.success(allMember);
    }
    /**
     * 查看某个社团成员
     */
    @GetMapping("")
    @ApiOperation("查看某个社团成员信息")
    public Result<StudentInfoVO> getMember(Integer studentNum,String studentName) {
        if(studentNum == null && studentName == null){
            return Result.error("学号和姓名不能同时为空");
        }
        StudentInfo member = null;
        //如果学号为空，根据姓名查找
        if(studentNum == null){
            member = memberManagementService.getByStudentName(studentName);
        }
        //如果姓名为空，根据学号查找
        else{
            member = memberManagementService.getByStudentNum(studentNum);
        }
        StudentInfoVO studentInfoVO = new StudentInfoVO();
        if(member == null){
            return Result.success();
        }
        BeanUtils.copyProperties(member,studentInfoVO);
        return Result.success(studentInfoVO);
        }

    /**
     * 查看退社成员信息
     */
    @GetMapping("quit")
    @ApiOperation("查看退社成员信息")
    public Result<PageResult> getQuitMember(Integer clubId, Integer pageNum, Integer pageSize) {
        PageResult quitMember = memberManagementService.getQuitMember(clubId, pageNum, pageSize);
        return Result.success(quitMember);
    }
    /**
     * 退社处理
     */
    @PostMapping("")
    @ApiOperation("退社处理")
    ///manager/memberManagement?quit=True
    public Result quitMember(@RequestBody ClubQuitAccessVO clubQuitAccessVO) {
        log.info("退社处理:{}", clubQuitAccessVO);
        memberManagementService.quitMember(clubQuitAccessVO.getApplyId(), clubQuitAccessVO.getState());
        return Result.success();
    }

}
