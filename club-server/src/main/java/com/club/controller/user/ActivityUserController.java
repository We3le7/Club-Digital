package com.club.controller.user;

import com.club.dto.ActivityOverviewUserDTO;
import com.club.result.PageResult;
import com.club.result.Result;
import com.club.service.ActivityOverviewUserService;
import com.club.vo.ActivityDetailsUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "活动相关接口")
@RestController
@RequestMapping("/user/activity")
@Slf4j
public class ActivityUserController {
    @Autowired
    private ActivityOverviewUserService activityOverviewUserService;

    /***
     * 用户按条件获取活动概览
     */
    @GetMapping("/get")
    @ApiOperation("用户按条件获取活动概览")
    public Result<PageResult> page(Integer pageNum,Integer pageSize,Integer userId,String title,
                                   String clubName,Integer district,Integer byViews,Integer byFavorites,
                                   Integer byReviewDatetime,String reviewDateStart,String reviewDateEnd){
        log.info("用户按照条件获取活动概览:{}",pageNum,pageSize,userId,title,clubName,district,byViews,byFavorites,byReviewDatetime,reviewDateStart,reviewDateEnd);
        PageResult pageResult =  activityOverviewUserService.getActivityOverviewByUser(pageNum,pageSize,userId,title,clubName,district,byViews,byFavorites,byReviewDatetime,reviewDateStart,reviewDateEnd);
        return Result.success(pageResult);
    }

    /***
     * 用户获取收藏的活动概览
     * @param userId
     * @return
     */
    @GetMapping("/get_favorites")
    @ApiOperation("获取用户收藏的活动概览")
    public Result<PageResult> getFavoriteActivityOverviewByUserId(Integer userId,Integer pageNum,Integer pageSize) {
        log.info("获取用户收藏的活动概览:{}",userId);
        PageResult pageResult =  activityOverviewUserService.getFavoriteActivityOverviewByUserId(userId,pageNum,pageSize);
        return Result.success(pageResult);
    }

    /***
     * 用户获取活动详情
     * @param userId
     * @param activityId
     * @return
     */
    @GetMapping("/get_activity")
    @ApiOperation("获取活动详情")
    public Result<ActivityDetailsUserVO> getActivityDetailsByActivityId(Integer activityId,Integer userId){
        log.info("获取活动详情:{},{}",userId,activityId);
        ActivityDetailsUserVO activityDetailsUserVO =  activityOverviewUserService.getActivityDetailsByActivityId(userId,activityId);
        return Result.success(activityDetailsUserVO);
    }

    /***
     * 用户获取个人报名的活动概览
     * @param userId
     * @return
     */
    @GetMapping("/get_registrations")
    @ApiOperation("获取用户的个人报名的活动概览")
    public Result<PageResult> getRegistrationsActivityOverviewByUserId(Integer userId,Integer pageNum,Integer pageSize) {
        log.info("获取用户收藏的活动概览:{},{},{}",userId,pageNum,pageSize);
        PageResult pageResult =  activityOverviewUserService.getRegistrationsActivityOverviewByUserId(userId,pageNum,pageSize);
        return Result.success(pageResult);
    }

    /***
     * 用户收藏活动
     * @param userId
     * @param activityId
     * @return
     */
    @PostMapping("/favorite_activity")
    @ApiOperation("用户收藏活动")
    public Result<String> favoriteActivity(Integer userId, Integer activityId){
        log.info("用户收藏活动:{},{}",userId,activityId);
        activityOverviewUserService.favoriteActivity(userId,activityId);
        return Result.success("收藏成功");
    }

    /***
     * 用户取消收藏活动
     * @param userId
     * @param activityId
     * @return
     */
    @DeleteMapping("/favorite_activity")
    @ApiOperation("用户取消收藏活动")
    public Result<String> cancelFavoriteActivity(Integer userId, Integer activityId){
        log.info("用户取消收藏活动:{},{}",userId,activityId);
        activityOverviewUserService.cancelFavoriteActivity(userId,activityId);
        return Result.success("取消收藏成功");
    }

    /***
     * 用户报名活动
     * @param userId
     * @param activityId
     * @return
     */
    @PostMapping("/register_activity")
    @ApiOperation("用户报名活动")
    public Result<String> registerActivity( Integer userId, Integer activityId){
        log.info("用户报名活动:{},{},{},{}",userId,activityId);
        activityOverviewUserService.registerActivity(userId,activityId);
        return Result.success("报名成功");
    }


    /***
     * 用户取消报名活动
     * @param userId
     * @param activityId
     * @return
     */
    @DeleteMapping("/register_activity")
    @ApiOperation("用户取消报名活动")
    public Result<String> cancelRegisterActivity( Integer userId, Integer activityId){
        log.info("用户取消报名活动:{},{}",userId,activityId);
        activityOverviewUserService.cancelRegisterActivity(userId,activityId);
        return Result.success("取消报名成功");
    }



}
