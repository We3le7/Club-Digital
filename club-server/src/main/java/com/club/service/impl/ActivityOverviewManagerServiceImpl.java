package com.club.service.impl;

import com.club.dto.ActivityAddManagerDTO;
import com.club.dto.ActivityOverviewManagerDTO;
import com.club.mapper.ActivityOverviewManagerMapper;
import com.club.result.PageResult;
import com.club.service.ActivityOverviewManagerService;
import com.club.vo.ActivityDetailsManagerVO;
import com.club.vo.ActivityOverviewManagerVO;
import com.club.vo.ClubInfoUserVO;
import com.club.vo.StudentInfoVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ActivityOverviewManagerServiceImpl implements ActivityOverviewManagerService {
    @Autowired
    private ActivityOverviewManagerMapper activityOverviewManagerMapper;
    /***
     * 社团管理端按照条件查询活动概览
     * @param pageNum,pageSize,userId,clubId,title,district,state,byViews,byFavorites,byReviewDatetime,reviewDateStart,reviewDateEnd
     * @return
     */
    @Override
    public PageResult getActivityOverviewByConditions(Integer pageNum,Integer pageSize,Integer userId,
                                                      Integer clubId,String title,Integer district,
                                                      Integer state,Integer byViews,Integer byFavorites,
                                                      Integer byReviewDatetime,String reviewDateStart,String reviewDateEnd) {
        PageHelper.startPage(pageNum, pageSize);
        Page<ActivityOverviewManagerVO> activityOverviewPage = activityOverviewManagerMapper.getActivityOverviewByConditions(pageNum,pageSize,userId,clubId,title,district,state,byViews,byFavorites,byReviewDatetime,reviewDateStart,reviewDateEnd);
        return new PageResult(activityOverviewPage.getTotal(), activityOverviewPage.getResult());
    }

    /***
     * 社团管理端获取该用户所管的社团信息
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageResult getClubInfoByUserId(Integer userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<ClubInfoUserVO>  clubInfoPage = activityOverviewManagerMapper.getClubInfoByUserId(userId);
        return new PageResult(clubInfoPage.getTotal(), clubInfoPage.getResult());
    }

    /***
     * 社团管理端获取报名列表
     * @param activityId
     * @return
     */
    @Override
    public List<StudentInfoVO> getStudentInfoByActivityId(Integer activityId) {
        return activityOverviewManagerMapper.getStudentInfoByActivityId(activityId);
    }

    /***
     * 社团管理端获取活动详情
     * @param userId
     * @param clubId
     * @param activityId
     * @return
     */
    @Override
    public ActivityDetailsManagerVO getActivityDetailsByActivityId(Integer userId, Integer clubId, Integer activityId) {
        //首先根据社团id和活动id查询出基本的活动信息
        ActivityDetailsManagerVO activityDetailsManagerVO = activityOverviewManagerMapper.getActivityDetailsByActivityId(clubId,activityId);

        //查询出照片url
        List<String> photoUrls = activityOverviewManagerMapper.getPhotoUrlsByActivityId(activityId,clubId);
        activityDetailsManagerVO.setActivityPictureUrl(photoUrls);

        //查询出文件url
        List<String> fileUrls = activityOverviewManagerMapper.getFileUrlsByActivityId(activityId,clubId);
        activityDetailsManagerVO.setActivityFileUrl(fileUrls);

        //返回VO对象
        return activityDetailsManagerVO;
    }

    /***
     * 社团管理端删除活动信息
     * @param activityId
     */
    @Override
    public void deleteActivityByActivityId(Integer activityId) {
        //首先删除活动收藏表里的收藏数据
        activityOverviewManagerMapper.deleteActivityFavoriteByActivityId(activityId);

        //删除活动文件里的活动数据
        activityOverviewManagerMapper.deleteActivityFileByActivityId(activityId);

        //删除活动照片里面的活动数据
        activityOverviewManagerMapper.deleteActivityPictureByActivityId(activityId);

        //删除活动报名表里的活动数据
        activityOverviewManagerMapper.deleteActivityRegistrationByActivityId(activityId);

        //最后删除活动表的活动数据
        activityOverviewManagerMapper.deleteActivityByActivityId(activityId);
    }

    /***
     * 社团管理端添加活动
     * @param activityAddManagerDTO
     */
    @Override
    public void addActivity(ActivityAddManagerDTO activityAddManagerDTO) {
        //如果registrationStart可以为空
        if(activityAddManagerDTO.getRegistrationStart().equals("")){
            activityAddManagerDTO.setRegistrationStart(null);
        }
        //如果registrationEnd可以为空
        if(activityAddManagerDTO.getRegistrationEnd().equals("")){
            activityAddManagerDTO.setRegistrationEnd(null);
        }
        //首先添加基本的活动信息
        activityOverviewManagerMapper.addActivity(activityAddManagerDTO);

        //获取插入活动数据生成的主键值
        Integer activityId = activityAddManagerDTO.getActivityId();

        //在活动照片表里添加照片的url信息
        activityOverviewManagerMapper.addActivityPicture(activityAddManagerDTO.getActivityPictureUrl(),activityId);

        //最后再在活动的文件表里添加文件url信息
        activityOverviewManagerMapper.addActivityFile(activityAddManagerDTO.getActivityFileUrl(),activityId);

    }

    /***
     * 社团管理端更新活动信息
     * @param activityAddManagerDTO
     */
    @Override
    public void updateActivity(ActivityAddManagerDTO activityAddManagerDTO) {
        //首先通过activity_id更新活动的基本信息
        activityOverviewManagerMapper.updateActivity(activityAddManagerDTO);

        //然后先根据活动id删除活动照片和活动的文件url
        activityOverviewManagerMapper.deleteActivityPictureByActivityId(activityAddManagerDTO.getActivityId());
        activityOverviewManagerMapper.deleteActivityFileByActivityId(activityAddManagerDTO.getActivityId());

        //然后再重新根据活动id添加活动照片和活动文件url
        activityOverviewManagerMapper.addActivityPicture(activityAddManagerDTO.getActivityPictureUrl(),activityAddManagerDTO.getActivityId());
        activityOverviewManagerMapper.addActivityFile(activityAddManagerDTO.getActivityFileUrl(),activityAddManagerDTO.getActivityId());
    }

}
