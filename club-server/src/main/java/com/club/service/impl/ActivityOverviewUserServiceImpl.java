package com.club.service.impl;

import com.club.dto.ActivityOverviewUserDTO;
import com.club.mapper.ActivityOverviewUserMapper;
import com.club.result.PageResult;
import com.club.service.ActivityOverviewUserService;
import com.club.vo.ActivityDetailsUserVO;
import com.club.vo.ActivityOverviewUserVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ActivityOverviewUserServiceImpl implements ActivityOverviewUserService {
    @Autowired
    private ActivityOverviewUserMapper activityOverviewUserMapper;
    /***
     * 用户按条件获取活动概览
     * @param pageNum,pageSize,userId,title,clubName,district,byViews,byFavorite,byReviewDatetime,reviewDateStart,reviewDateEnd
     * @return
     */
    @Override
    public PageResult getActivityOverviewByUser(Integer pageNum,Integer pageSize,Integer userId,String title,
                                                String clubName,Integer district,Integer byViews,Integer byFavorites,
                                                Integer byReviewDatetime,String reviewDateStart,String reviewDateEnd) {
        PageHelper.startPage(pageNum, pageSize);
        Page<ActivityOverviewUserVO> activityOverviewPage = activityOverviewUserMapper.getActivityOverviewByUser(pageNum,pageSize,userId,title,clubName,district,byViews,byFavorites,byReviewDatetime,reviewDateStart,reviewDateEnd);
        return new PageResult(activityOverviewPage.getTotal(), activityOverviewPage.getResult());
    }

    /***
     * 获取用户收藏的活动概览
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageResult getFavoriteActivityOverviewByUserId(Integer userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<ActivityOverviewUserVO> activityOverviewPage = activityOverviewUserMapper.getFavoriteActivityOverviewByUserId(userId);
        return new PageResult(activityOverviewPage.getTotal(), activityOverviewPage.getResult());
    }

    /***
     * 获取用户活动详情
     * @param userId
     * @param activityId
     * @return
     */
    @Override
    public ActivityDetailsUserVO getActivityDetailsByActivityId(Integer userId, Integer activityId) {
        //首先将该活动的views字段加一
        activityOverviewUserMapper.addViews(activityId);

        //首先根据活动Id查询出基本的一些信息
        ActivityDetailsUserVO  activityDetailsUserVO = activityOverviewUserMapper.getActivityDetailsByActivityId(userId,activityId);

        //设置文章Id
        activityDetailsUserVO.setActivityId(activityId);

        //根据活动Id查询出所有的照片URL
        List<String> photoUrls = activityOverviewUserMapper.getPhotoUrlsByActivityId(activityId);
        activityDetailsUserVO.setActivityPictureUrl(photoUrls);

        //返回VO对象
        return activityDetailsUserVO;
    }

    /***
     * 获取用户的个人报名的活动概览
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageResult getRegistrationsActivityOverviewByUserId(Integer userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<ActivityOverviewUserVO> activityOverviewPage = activityOverviewUserMapper.getRegistrationsActivityOverviewByUserId(userId);
        return new PageResult(activityOverviewPage.getTotal(), activityOverviewPage.getResult());
    }

    /***
     * 用户收藏活动
     * @param userId
     * @param activityId
     */
    @Override
    public void favoriteActivity(Integer userId, Integer activityId) {
        //TODO 需要判断用户是否已经收藏过该活动


        //直接在favorite_activity表中插入一条数据
        activityOverviewUserMapper.favoriteActivity(userId,activityId);

        //需要将activity表中的favorites字段加1
        activityOverviewUserMapper.addFavorites(activityId);
    }

    /***
     * 用户取消收藏活动
     * @param userId
     * @param activityId
     */
    @Override
    public void cancelFavoriteActivity(Integer userId, Integer activityId) {
        //直接在favorite_activity表中删除一条数据
        activityOverviewUserMapper.cancelFavoriteActivity(userId,activityId);

        //还需要将activity表中的favorites字段减1
        activityOverviewUserMapper.reduceFavorites(activityId);
    }

    /***
     * 用户报名活动
     * @param userId
     * @param activityId
     *
     */
    @Override
    public void registerActivity(Integer userId, Integer activityId) {
        //首先获取当前的时间
        LocalDateTime registrationTime = LocalDateTime.now();

        //直接在activity_registration上进行数据添加即可
        activityOverviewUserMapper.registerActivity(userId,activityId,registrationTime);
    }

    /***
     * 用户取消报名活动
     * @param userId
     * @param activityId
     */
    @Override
    public void cancelRegisterActivity(Integer userId, Integer activityId) {
        //直接在activity_registration上进行数据删除即可
        activityOverviewUserMapper.cancelRegisterActivity(userId,activityId);
    }
}
