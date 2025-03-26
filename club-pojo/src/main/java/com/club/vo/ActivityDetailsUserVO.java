package com.club.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ActivityDetailsUserVO {
    //活动Id
    private Integer activityId;

    //活动标题
    private String title;

    //活动校区
    private String district;

    //活动的详细地址
    private String specificAddr;

    //活动的浏览量
    private Integer views;

    //活动的收藏量
    private Integer favorites;

    //活动的举办开始时间
    private String startDatetime;

    //活动的举办结束时间
    private String endDatetime;

    //活动是否需要报名——修改
    private Boolean requireRegistration;

    //报名开始时间
    private String registrationStart;

    //报名结束时间
    private String registrationEnd;

    //活动审批时间/活动开始时间
    private String reviewDatetime;

    //负责人
    private String responsiblePerson;

    //电话号码
    private String phoneNum;

    //活动的详细内容
    private String content;

    //活动的社团Id
    private Integer clubId;

    //活动的社团名称
    private String clubName;

    //活动类型Id
    private Integer activityTypeId;

    //活动类型——新增
    private String activityType;

    //是否被收藏
    private Boolean isFavorites;

    //是否已经报名
    private Boolean isRegistered;

    //活动的照片url
    private List<String> activityPictureUrl=new ArrayList<>();

}
