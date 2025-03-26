package com.club.dto;

import lombok.Data;

@Data
public class ActivityOverviewUserDTO {
    //表示当前的页码
    private Integer pageNum;

    //表示当前每一页的大小
    private Integer pageSize;

    //用户Id
    private Integer userId;

    //活动标题
    private String title;

    //社团名称
    private String clubName;

    //校区Id
    private Integer district;

    //是否按照浏览数量排序
    private Integer byViews;

    //是否按照收藏量排序
    private Integer byFavorites;

    //是否按照发布时间排序
    private Integer byReviewDatetime;

    //发布的初始时间
    private String reviewDateStart;

    //发布的结束时间
    private String reviewDateEnd;

}
