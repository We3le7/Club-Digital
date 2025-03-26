package com.club.controller.graph;

import com.club.result.PageResult;
import com.club.result.Result;
import com.club.service.ActivityLeagueService;
import com.club.service.ShowClubInfoService;
import com.club.utils.AliOssUtil;
import com.club.vo.ClubInfoStaticVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "社团信息统计相关接口")
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/admin")
@Slf4j
public class ShowClubInfoController {
   @Autowired
    private ShowClubInfoService showClubInfoService;

    @GetMapping("/showClubInfo")
    @ApiOperation("社团信息统计")
    public Result<List<ClubInfoStaticVO>> showClubInfo(Integer departmentId) {
        log.info("社团信息统计:{}", departmentId);
        List<ClubInfoStaticVO> clubInfoStaticVO = showClubInfoService.showClubInfo(departmentId);
        return Result.success(clubInfoStaticVO);
    }
}
