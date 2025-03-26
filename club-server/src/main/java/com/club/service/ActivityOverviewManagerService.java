package com.club.service;

import com.club.dto.ActivityAddManagerDTO;
import com.club.dto.ActivityOverviewManagerDTO;
import com.club.result.PageResult;
import com.club.vo.ActivityDetailsManagerVO;
import com.club.vo.StudentInfoVO;

import java.util.List;

public interface ActivityOverviewManagerService {
    /***
     * 社团管理端按照条件查询活动概览
     * @param pageNum,pageSize,userId,clubId,title,district,state,byViews,byFavorites,byReviewDatetime,reviewDateStart,reviewDateEnd
     * @return
     */
    PageResult getActivityOverviewByConditions(Integer pageNum,Integer pageSize,Integer userId,
                                               Integer clubId,String title,Integer district,
                                               Integer state,Integer byViews,Integer byFavorites,
                                               Integer byReviewDatetime,String reviewDateStart,String reviewDateEnd);

    /***
     * 社团管理端获取该用户所管的社团信息
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult getClubInfoByUserId(Integer userId, Integer pageNum, Integer pageSize);

    /***
     * 社团管理端获取报名列表
     * @param activityId
     * @return
     */
    List<StudentInfoVO> getStudentInfoByActivityId(Integer activityId);

    /***
     * 社团管理端获取活动详情
     * @param userId
     * @param clubId
     * @param activityId
     * @return
     */
    ActivityDetailsManagerVO getActivityDetailsByActivityId(Integer userId, Integer clubId, Integer activityId);

    /***
     * 社团管理端删除活动
     * @param activityId
     */
    void deleteActivityByActivityId(Integer activityId);

    /***
     * 社团管理端添加活动
     * @param activityAddManagerVO
     */
    void addActivity(ActivityAddManagerDTO activityAddManagerVO);

    /***
     * 社团管理端修改活动信息
     * @param activityAddManagerDTO
     */
    void updateActivity(ActivityAddManagerDTO activityAddManagerDTO);
}
