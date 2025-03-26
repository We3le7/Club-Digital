package com.club.service;

import com.club.dto.ActivityOverviewAdminDTO;
import com.club.result.PageResult;
import com.club.vo.ActivityDetailsAdminVO;
import com.club.vo.ClubInfoList;

import java.util.List;

public interface ActivityOverviewAdminService {

    /***
     * 机关部处根据条件查看活动概览
     * @param pageNum,pageSize,department,title,clubId,district,state,byApplicationDatetime
     * @return
     */
    PageResult getActivityOverviewByConditions(Integer pageNum,Integer pageSize,Integer departmentId,
                                               String title,Integer clubId,Integer district,
                                               Integer state,Boolean byApplicationDatetime);

    /***
     * 机关部处获取文章详情
     * @param activityId
     * @return
     */
    ActivityDetailsAdminVO getActivityDetailsByActivityId(Integer activityId);

    /***
     * 机关部处审核活动
     * @param activityId
     * @param state
     * @param comment
     */
    void auditActivity(Integer activityId, Integer state, String comment,String reviewDatetime);

    /***
     * 机关部处获取所管辖的社团列表
     * @param departmentId
     * @return
     */
    List<ClubInfoList> getClubList(Integer departmentId);
}
