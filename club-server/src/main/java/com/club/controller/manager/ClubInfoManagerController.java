package com.club.controller.manager;

import com.club.dto.ClubInfoManagerDTO;
import com.club.dto.ClubInfoUserDTO;
import com.club.result.Result;
import com.club.service.ClubManagerService;
import com.club.vo.ActivityVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "社团相关接口")
@RestController
@RequestMapping("/user/manager")
@Slf4j
public class ClubInfoManagerController {
    //通过调用user里面的getbyid接口查询某一个社团的信息
    @Autowired
    private ClubManagerService clubManagerService;

    /***
     * 修改社团相关信息
     * @param clubInfoManagerDTO
     * @return
     */
    @PutMapping("/club")
    @ApiOperation("修改社团相关信息")
    public Result update(ClubInfoManagerDTO clubInfoManagerDTO){
        log.info("修改社团相关信息:{}",clubInfoManagerDTO);
        clubManagerService.updateClubInfo(clubInfoManagerDTO);
        return Result.success("修改成功");
    }

    /***
     * 获取社团的活动的相关信息
     * @param clubId
     * @return
     */
    @GetMapping("/get_activity")
    @ApiOperation("获取社团活动")
    public Result<List<ActivityVO>> getActivityByClubId(Integer clubId){
        log.info("获取社团活动");
        List<ActivityVO> activityVO = clubManagerService.getActivityByClubId(clubId);
        return Result.success(activityVO);
    }

}