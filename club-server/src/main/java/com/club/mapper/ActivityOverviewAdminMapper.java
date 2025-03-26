package com.club.mapper;

import com.club.dto.ActivityOverviewAdminDTO;
import com.club.vo.ActivityDetailsAdminVO;
import com.club.vo.ActivityOverviewAdminVO;
import com.club.vo.ActivityOverviewManagerVO;
import com.club.vo.ClubInfoList;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ActivityOverviewAdminMapper {
    /***
     * 机关部处根据条件查看活动概览
     * @param pageNum,pageSize,department,title,clubId,district,state,byApplicationDatetime
     * @return
     */
    Page<ActivityOverviewAdminVO> getActivityOverviewByConditions(Integer pageNum, Integer pageSize, Integer departmentId,
                                                                  String title, Integer clubId, Integer district,
                                                                  Integer state, Boolean byApplicationDatetime);

    /***
     * 机关部处根据活动id查询出基本的活动信息
     * @param activityId
     * @return
     */
    ActivityDetailsAdminVO getActivityDetailsByActivityId(Integer activityId);

    /***
     * 机关部处根据活动Id查询出活动的照片url
     * @param activityId
     * @return
     */
    List<String> getPhotoUrlsByActivityId(Integer activityId);

    /***
     * 根据活动Id查询出活动的文件url
     * @param activityId
     * @return
     */
    List<String> getFileUrlsByActivityId(Integer activityId);

    /***
     * 机关部处审核活动
     * @param activityId
     * @param state
     * @param comment
     */
    @Update("update activity set state=#{state},comment=#{comment},review_datetime=#{reviewDatetime} where activity_id=#{activityId}")
    void auditActivity(Integer activityId, Integer state, String comment,String reviewDatetime);

    /***
     * 机关部处获取所管辖的社团列表
     * @param departmentId
     * @return
     */
    @Select("select club_id,club_name from club_info where department_id=#{departmentId}")
    List<ClubInfoList> getClubList(Integer departmentId);
}
