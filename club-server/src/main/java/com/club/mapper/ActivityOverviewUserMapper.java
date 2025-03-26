package com.club.mapper;

import com.club.dto.ActivityOverviewUserDTO;
import com.club.vo.ActivityDetailsUserVO;
import com.club.vo.ActivityOverviewUserVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ActivityOverviewUserMapper {
    /***
     * 用户按条件获取活动概览
     * @param pageNum,pageSize,userId,title,clubName,district,byViews,byFavorite,byReviewDatetime,reviewDateStart,reviewDateEnd
     * @return
     */
    Page<ActivityOverviewUserVO> getActivityOverviewByUser(Integer pageNum,Integer pageSize,Integer userId,String title,
                                                           String clubName,Integer district,Integer byViews,Integer byFavorites,
                                                           Integer byReviewDatetime,String reviewDateStart,String reviewDateEnd);

    /***
     * 获取用户收藏的活动概览
     * @param userId
     * @return
     */
    Page<ActivityOverviewUserVO> getFavoriteActivityOverviewByUserId(Integer userId);

    /***
     * 根据文章Id查询出基本的活动信息
     * @param userId
     * @param activityId
     * @return
     */
    ActivityDetailsUserVO getActivityDetailsByActivityId(Integer userId,Integer activityId );

    /***
     * 根据活动Id查询出活动的图片
     * @param activityId
     * @return
     */
    List<String> getPhotoUrlsByActivityId(Integer activityId);

    /***
     * 根据用户Id查询出用户报名的活动概览
     * @param userId
     * @return
     */
    Page<ActivityOverviewUserVO> getRegistrationsActivityOverviewByUserId(Integer userId);

    /***
     * 用户收藏活动
     * @param userId
     * @param activityId
     */
    @Insert("insert into activity_favorite(student_id,activity_id) values(#{userId},#{activityId})")
    void favoriteActivity(Integer userId, Integer activityId);

    /***
     * 用户取消收藏活动
     * @param userId
     * @param activityId
     */
    @Delete("delete from activity_favorite where student_id=#{userId} and activity_id=#{activityId}")
    void cancelFavoriteActivity(Integer userId, Integer activityId);

    /***
     * 用户浏览活动
     * @param activityId
     */
    @Update("update activity set views=views+1 where activity_id=#{activityId}")
    void viewActivity(Integer activityId);

    /***
     * 用户报名活动
     * @param userId
     * @param activityId
     * @param registrationTime
     *
     */
    @Insert("insert into activity_registration(student_id,activity_id,registration_time,is_attending) values(#{userId},#{activityId},#{registrationTime},1)")
    void registerActivity(Integer userId, Integer activityId, LocalDateTime registrationTime);

    /***
     * 用户取消报名活动
     * @param userId
     * @param activityId
     */
    @Delete("delete from activity_registration where student_id=#{userId} and activity_id=#{activityId}")
    void cancelRegisterActivity(Integer userId, Integer activityId);

    /***
     * 用户收藏活动后，需要将activity表中的favorites加1
     * @param activityId
     */
    @Update("update activity set favorites=favorites+1 where activity_id=#{activityId}")
    void addFavorites(Integer activityId);

    /***
     * 用户取消收藏活动后，需要将activity表中的favorites减1
     * @param activityId
     */
    @Update("update activity set favorites=favorites-1 where activity_id=#{activityId}")
    void reduceFavorites(Integer activityId);

    /***
     * 用户在点击活动详情时，需要将views字段加1
     * @param activityId
     */
    @Update("update activity set views=views+1 where activity_id=#{activityId}")
    void addViews(Integer activityId);
}
