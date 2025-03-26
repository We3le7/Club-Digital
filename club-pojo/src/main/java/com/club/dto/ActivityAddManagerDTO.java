package com.club.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//添加活动
@Data
public class ActivityAddManagerDTO {
    //活动Id
    private Integer activityId;

    //活动标题
    private String title;

    //活动校区
    private Integer district;

    //活动的详细地址
    private String specificAddr;

    //活动的浏览量
    private Integer views;

    //活动的收藏量
    private Integer favorites;

    //活动的审核状态
    private Integer state;

    //活动的审核意见
    private String comment;

    //活动的举办开始时间
    private String startDatetime;

    //活动的举办结束时间
    private String endDatetime;

    //活动是否需要报名
    private Boolean requireRegistration;

    //报名开始时间
    private String registrationStart;

    //报名结束时间
    private String registrationEnd;

    //活动的发布时间
    private String applicationDatetime;

    //活动的面向对象
    private Integer visibility;

    //负责人
    private String responsiblePerson;

    //电话号码
    private String phoneNum;

    //活动的详细内容
    private String content;

    //活动的发起社团
    private Integer clubId;

    //活动的部门负责人
    private Integer departmentId;

    //活动类型Id
    private Integer activityTypeId;

    //活动的照片url
    private List<String> activityPictureUrl=new ArrayList<>();

    //活动的文件url
    private List<String> activityFileUrl=new ArrayList<>();
}
