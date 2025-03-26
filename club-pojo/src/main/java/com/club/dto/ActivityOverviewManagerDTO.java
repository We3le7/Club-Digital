package com.club.dto;

import lombok.Data;

@Data
public class ActivityOverviewManagerDTO {
    //表示当前的页码
    private Integer pageNum;

    //表示当前每一页的大小
    private Integer pageSize;

    //用户Id
    private Integer userId;

    //社团Id
    private Integer clubId;

    //活动标题
    private String title;

    //校区Id
    private Integer district;

    //审核状态
    private Integer state;

    //是否按照浏览数量排序
    private Integer byViews;

    //是否按照收藏量排序
    private Integer byFavorites;

    //是否按照发布时间排序
    private Integer byReviewDatetime;

    //发布时间的开始值
    private String reviewDateStart;

    //发布时间的结束值
    private String reviewDateEnd;
}
