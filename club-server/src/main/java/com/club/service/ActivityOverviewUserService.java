package com.club.service;

import com.club.dto.ActivityOverviewUserDTO;
import com.club.result.PageResult;
import com.club.vo.ActivityDetailsUserVO;

public interface ActivityOverviewUserService {
    /***
     * 用户按条件获取活动概览
     * @param pageNum,pageSize,userId,title,clubName,district,byViews,byFavorites,byReviewDatetime,reviewDateStart,reviewDateEnd
     * @return
     */
    PageResult getActivityOverviewByUser(Integer pageNum,Integer pageSize,Integer userId,String title,
                                         String clubName,Integer district,Integer byViews,Integer byFavorites,
                                         Integer byReviewDatetime,String reviewDateStart,String reviewDateEnd);

    /***
     * 获取用户收藏的活动概览
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult getFavoriteActivityOverviewByUserId(Integer userId, Integer pageNum, Integer pageSize);

    /***
     * 获取用户活动详情
     * @param userId
     * @param activityId
     * @return
     */
    ActivityDetailsUserVO getActivityDetailsByActivityId(Integer userId, Integer activityId);

    /***
     * 获取用户个人报名的活动概览
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult getRegistrationsActivityOverviewByUserId(Integer userId, Integer pageNum, Integer pageSize);

    /***
     * 用户收藏活动
     * @param userId
     * @param activityId
     */
    void favoriteActivity(Integer userId, Integer activityId);

    /***
     * 用户取消收藏活动
     * @param userId
     * @param activityId
     */
    void cancelFavoriteActivity(Integer userId, Integer activityId);

    /***
     * 用户报名活动
     * @param userId
     * @param activityId
     *
     */
    void registerActivity(Integer userId, Integer activityId);

    /***
     * 用户取消报名活动
     * @param userId
     * @param activityId
     */
    void cancelRegisterActivity(Integer userId, Integer activityId);
}
