package com.club.service;

import com.club.vo.ClubActivitySummaryVO;

public interface ClubActivitySummaryService {
    //获取所管的社团的每一个月的活动信息
    ClubActivitySummaryVO getClubActivitiesSummary(Integer departmentId);
}
