package com.club.controller.admin;

import com.club.entity.DepartmentInfo;
import com.club.result.Result;
import com.club.service.ClubActivitySummaryService;
import com.club.vo.ClubActivitySummaryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/admin")
@Api(tags = "机关部处用户相关")

public class ClubActivitySummary {
    @Autowired
    private ClubActivitySummaryService clubActivitySummaryService;
    /***
     * 获取所管社团的活动信息
     * @param departmentId
     * @return
     */
    @GetMapping("/get_club_activities_summary")
    @ApiOperation(value = "获取所管社团的活动信息")
    public Result<ClubActivitySummaryVO> getClubActivitiesSummary(Integer departmentId) {
        log.info("获取所管社团的活动信息：{}", departmentId);
        ClubActivitySummaryVO clubActivitySummaryVO = clubActivitySummaryService.getClubActivitiesSummary(departmentId);
        return Result.success(clubActivitySummaryVO);
    }
}
