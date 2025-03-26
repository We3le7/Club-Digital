package com.club.vo;

import lombok.Data;

@Data
public class ActivityOverviewAdminVO {
    //活动Id
    private Integer activityId;

    //活动标题
    private String title;

    //活动内容
    private String content;

    //活动的审核状态——新增
    private Integer state;

    //活动的社团名称
    private String clubName;

    //活动的校区类型——新增
    private String district;

    //活动申请时间
    private String applicationDatetime;

    //活动的第一张照片url
    private String activityPictureUrl;

}
