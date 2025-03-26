package com.club.vo;

import lombok.Data;

@Data
public class ActivityOverviewUserVO {
    //活动Id
    private Integer activityId;

    //社团Id
    private Integer clubId;

    //活动标题
    private String title;
    
    //活动内容
    private String content;

    //活动浏览数
    private Integer views;

    //活动收藏数
    private Integer favorites;

    //活动举办的社团
    private String clubName;

    //活动审批时间/活动发布时间
    private String reviewDatetime;

    //活动的第一张照片URL
    private String activityPictureUrl;

}
