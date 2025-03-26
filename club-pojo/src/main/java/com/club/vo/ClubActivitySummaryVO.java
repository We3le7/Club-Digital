package com.club.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ClubActivitySummaryVO {
    //近一年所经历的月份
    private List<String> months=new ArrayList<>();

    //表示某一个社团的某一个月的活动列表
    private Map<String, ClubActivitiesVO> activities = new HashMap<>();

    // 每个社团每个月的活动总量
    private Map<String, List<Integer>> activityCounts = new HashMap<>();
}
