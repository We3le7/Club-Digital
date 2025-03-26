package com.club.mapper;

import com.club.dto.ActivityAddManagerDTO;
import com.club.dto.ActivityOverviewManagerDTO;
import com.club.vo.ActivityDetailsManagerVO;
import com.club.vo.ActivityOverviewManagerVO;
import com.club.vo.ClubInfoUserVO;
import com.club.vo.StudentInfoVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityOverviewManagerMapper {
    /***
     * 社团管理端根据条件查询活动概览
     * @param pageNum,pageSize,userId,clubId,title,district,state,byViews,byFavorites,byReviewDatetime,reviewDateStart,reviewDateEnd
     * @return
     */
    Page<ActivityOverviewManagerVO> getActivityOverviewByConditions(Integer pageNum,Integer pageSize,Integer userId,
                                                                    Integer clubId,String title,Integer district,
                                                                    Integer state,Integer byViews,Integer byFavorites,
                                                                    Integer byReviewDatetime,String reviewDateStart,String reviewDateEnd);

    /***
     * 社团管理端获取该用户所管的社团信息
     * @param userId
     * @return
     */
    Page<ClubInfoUserVO> getClubInfoByUserId(Integer userId);

    /***
     * 社团管理端获取报名列表
     * @param activityId
     * @return
     */
    List<StudentInfoVO> getStudentInfoByActivityId(Integer activityId);

    /**
     * 社团管理端获取活动详情
     * @param clubId
     * @param activityId
     * @return
     */
    ActivityDetailsManagerVO getActivityDetailsByActivityId(Integer clubId, Integer activityId);

    /***
     * 社团管理端根据活动id和社团id获取活动图片
     * @param activityId
     * @param clubId
     * @return
     */
    List<String> getPhotoUrlsByActivityId(Integer activityId, Integer clubId);

    /***
     * 社团管理端根据活动id和社团id获取活动附件
     * @param activityId
     * @param clubId
     * @return
     */
    List<String> getFileUrlsByActivityId(Integer activityId, Integer clubId);

    /***
     * 社团管理端根据活动Id删除活动的收藏数据
     * @param activityId
     */
    @Delete("delete from activity_favorite where activity_id=#{activityId}")
    void deleteActivityFavoriteByActivityId(Integer activityId);

    /***
     * 社团管理端根据活动Id删除活动的活动文件数据
     * @param activityId
     */
    @Delete("delete from activity_file where activity_id=#{activityId}")
    void deleteActivityFileByActivityId(Integer activityId);

    /***
     * 社团管理端根据活动Id删除活动的活动图片数据
     * @param activityId
     */
    @Delete("delete from activity_picture where activity_id=#{activityId}")
    void deleteActivityPictureByActivityId(Integer activityId);

    /**
     * 社团管理端根据活动Id删除活动的活动报名数据
     * @param activityId
     */
    @Delete("delete from activity_registration where activity_id=#{activityId}")
    void deleteActivityRegistrationByActivityId(Integer activityId);

    /***
     * 社团管理端根据活动Id删除活动数据
     * @param activityId
     */
    @Delete("delete from activity where activity_id=#{activityId}")
    void deleteActivityByActivityId(Integer activityId);

    /***
     * 社团管理端添加新的活动的基本信息
     * @param activityAddManagerDTO
     */
    void addActivity(ActivityAddManagerDTO activityAddManagerDTO);

    /***
     * 社团管理端添加活动的照片url信息
     * @param activityPictureUrl
     * @param activityId
     */
    void addActivityPicture(List<String> activityPictureUrl, Integer activityId);

    /***
     * 社团管理端添加活动的附件url信息
     * @param activityFileUrl
     * @param activityId
     */
    void addActivityFile(List<String> activityFileUrl, Integer activityId);

    /***
     * 社团管理端更新活动的基本信息
     * @param activityAddManagerDTO
     */
    void updateActivity(ActivityAddManagerDTO activityAddManagerDTO);
}
