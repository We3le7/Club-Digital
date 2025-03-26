package com.club.service.impl;

import com.club.dto.ActivityOverviewAdminDTO;
import com.club.mapper.ActivityOverviewAdminMapper;
import com.club.result.PageResult;
import com.club.service.ActivityOverviewAdminService;
import com.club.vo.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ActivityOverviewAdminServiceImpl implements ActivityOverviewAdminService {
    @Autowired
    private ActivityOverviewAdminMapper activityOverviewAdminMapper;

    /***
     * 机关部处根据条件查看活动概览
     * @param pageNum,pageSize,department,title,clubId,district,state,byApplicationDatetime
     * @return
     */
    @Override
    public PageResult getActivityOverviewByConditions(Integer pageNum,Integer pageSize,Integer departmentId,
                                                      String title,Integer clubId,Integer district,
                                                      Integer state,Boolean byApplicationDatetime){
        log.info("社团管理端按照条件获取活动概览:{}",pageNum,pageSize,departmentId,title,clubId,district,state,byApplicationDatetime);
        PageHelper.startPage(pageNum,pageSize);
        Page<ActivityOverviewAdminVO> activityOverviewPage = activityOverviewAdminMapper.getActivityOverviewByConditions(pageNum,pageSize,departmentId,title,clubId,district,state,byApplicationDatetime);
        return new PageResult(activityOverviewPage.getTotal(), activityOverviewPage.getResult());
    }

    /***
     * 机关部处获取文章详情
     * @param activityId
     * @return
     */
    @Override
    public ActivityDetailsAdminVO getActivityDetailsByActivityId(Integer activityId) {
        //首先根据活动id查询出基本的活动信息
        ActivityDetailsAdminVO activityDetailsAdminVO = activityOverviewAdminMapper.getActivityDetailsByActivityId(activityId);

        //查询出照片url
        List<String> photoUrls = activityOverviewAdminMapper.getPhotoUrlsByActivityId(activityId);
        activityDetailsAdminVO.setActivityPictureUrl(photoUrls);

        //查询出文件url
        List<String> fileUrls = activityOverviewAdminMapper.getFileUrlsByActivityId(activityId);
        activityDetailsAdminVO.setActivityFileUrl(fileUrls);

        //返回VO对象
        return activityDetailsAdminVO;
    }

    /***
     * 机关部处审核活动
     * @param activityId
     * @param state
     * @param comment
     */
    @Override
    public void auditActivity(Integer activityId, Integer state, String comment,String reviewDatetime) {
        //直接在activity表中修改更新审核数据
        activityOverviewAdminMapper.auditActivity(activityId,state,comment,reviewDatetime);
    }

    /***
     * 机关部处获取所管辖的社团列表
     * @param departmentId
     * @return
     */
    @Override
    public List<ClubInfoList> getClubList(Integer departmentId) {
        //直接在数据库里面进行查询获取即可
        return activityOverviewAdminMapper.getClubList(departmentId);
    }
}
