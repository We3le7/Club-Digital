package com.club.vo;

import lombok.Data;

@Data
public class ActivityOverviewManagerVO {
    //活动Id
    private Integer activityId;

    //活动标题
    private String title;

    //活动内容
    private String content;

    //所在校区
    private String district;

    //活动浏览数
    private Integer views;

    //活动收藏数
    private Integer favorites;

    //审核状态
    private Integer state;

    //活动审批时间/活动发布时间
    private String reviewDatetime;

    //活动的第一张照片URL
    private String activityPictureUrl;
}
