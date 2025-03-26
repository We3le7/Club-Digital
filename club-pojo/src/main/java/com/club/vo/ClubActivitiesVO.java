package com.club.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ClubActivitiesVO {
    //表示某一个社团某月的活动列表
    private Map<String, List<ActivityVO>> activities = new HashMap<>();
}
