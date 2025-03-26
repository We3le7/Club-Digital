package com.club.controller.admin;

import com.club.dto.ActivityOverviewAdminDTO;
import com.club.dto.ActivityOverviewManagerDTO;
import com.club.result.PageResult;
import com.club.result.Result;
import com.club.service.ActivityOverviewAdminService;
import com.club.vo.ActivityDetailsAdminVO;
import com.club.vo.ActivityDetailsUserVO;
import com.club.vo.ClubInfoList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin/activity")
@Api(tags = "活动相关接口")
public class ActivityAdminController {
    @Autowired
    private ActivityOverviewAdminService activityOverviewAdminService;

    /***
     * 机关部处根据条件查看活动概览
     * @param pageNum,pageSize,department,title,clubId,district,state,byApplicationDatetime
     * @return
     */
    @GetMapping("get")
    @ApiOperation("机关部处端按照条件获取活动概览")
    public Result<PageResult> getActivityOverviewByConditions(Integer pageNum,Integer pageSize,Integer departmentId,
                                                              String title,Integer clubId,Integer district,
                                                              Integer state,Boolean byApplicationDatetime){
        log.info("社团管理端按照条件获取活动概览:{}",pageNum,pageSize,departmentId,title,clubId,district,state,byApplicationDatetime);
        PageResult pageResult =  activityOverviewAdminService.getActivityOverviewByConditions(pageNum,pageSize,departmentId,title,clubId,district,state,byApplicationDatetime);
        return Result.success(pageResult);
    }

    /***
     * 机关部处获取文章详情
     * @param activityId
     * @return
     */
    @GetMapping("get_activity")
    @ApiOperation("机关部处获取文章详情")
    public Result<ActivityDetailsAdminVO> getActivityDetailsByActivityId(Integer activityId){
        log.info("获取活动详情:{}",activityId);
        ActivityDetailsAdminVO activityDetailsAdminVO =  activityOverviewAdminService.getActivityDetailsByActivityId(activityId);
        return Result.success(activityDetailsAdminVO);
    }

    /***
     * 机关部处审核活动
     * @param activityId
     * @param state
     * @param comment
     * @return
     */
    @PutMapping("audit_activity")
    @ApiOperation("机关部处审核活动")
    public Result<String> auditActivity(Integer activityId, Integer state, String comment){
        log.info("审核活动:{},{},{}",activityId,state,comment);
        LocalDateTime currentDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        activityOverviewAdminService.auditActivity(activityId,state,comment,formattedDateTime);
        return Result.success("审核成功");
    }

    /***
     * 机关部处获取所管辖的社团列表
     * @param departmentId
     * @return
     */
    @GetMapping("get_club_list")
    @ApiOperation("机关部处获取所管辖的社团列表")
    public Result<List<ClubInfoList>> getClubList(Integer departmentId){
        log.info("获取所管辖的社团列表");
        List<ClubInfoList> clubList = activityOverviewAdminService.getClubList(departmentId);
        return Result.success(clubList);
    }
}
