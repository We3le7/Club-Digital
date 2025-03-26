package com.club.constant;

/***
 * 活动相关类型常量
 */
public class ActivityConstant {
    /***
     * 审核状态常量
     */
    //未审核
    public static final Integer UNREVIEWED = 0;

    //审核通过
    public static final Integer APPROVED=1;

    //审核未通过
    public static final Integer NOT_APPROVED=2;

    /***
     * 是否需要报名
     */
    //不需要报名
    public static final Integer NOT_REQUIRES_REGISTRATION=0;

    //需要报名
    public static final Integer REQUIRES_REGISTRATION=1;

    /***
     * 活动对象
     */
    //所有人
    public static final Integer ALL_PEOPLE=0;

    //社团成员
    public static final Integer CLUB_MEMBER=1;
}
