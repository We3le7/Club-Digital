package com.club.service.impl;

import com.club.mapper.ClubActivitySummaryMapper;
import com.club.service.ClubActivitySummaryService;
import com.club.vo.ActivityVO;
import com.club.vo.ClubActivitiesVO;
import com.club.vo.ClubActivitySummaryVO;
import com.club.vo.Club;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ClubActivitySummaryServiceImpl implements ClubActivitySummaryService {
    @Autowired
    private ClubActivitySummaryMapper clubActivitySummaryMapper;

    @Override
    public ClubActivitySummaryVO getClubActivitiesSummary(Integer departmentId) {
        //创建一个新的ClubActivitySummaryVO对象
        ClubActivitySummaryVO summaryVO = new ClubActivitySummaryVO();

        //1.首先获取当前的系统的时间，然后将VO中的months填上，从1到现在目前的月份
        // 获取当前系统时间的月份
        LocalDate currentDate = LocalDate.now();
        Month currentMonth = currentDate.getMonth();
        //获取当前系统的年份
        Integer currentYear = currentDate.getYear();

        // 填充月份列表，从1月到当前月份
        List<String> months = new ArrayList<>();
        for (int i = 1; i <= currentMonth.getValue(); i++) {
            String month = i + "月";
            months.add(month);
        }
        summaryVO.setMonths(months);

        //首先获取当前department对应的club的id和name
        List<Club> clubs = clubActivitySummaryMapper.getClubsByDepartmentId(departmentId);

        //2.遍历每一个社团，然后遍历每一个月份，然后获取当前社团在当前月份的活动信息id和name
        Map<String, ClubActivitiesVO> clubActivities = new HashMap<>();
        for(Club club : clubs){
            ClubActivitiesVO activitiesByMonth = new ClubActivitiesVO();
            Map<String, List<ActivityVO>> activitiesByMonths = new HashMap<>();

            for (int i = 1; i <= currentMonth.getValue(); i++) {
                List<ActivityVO> activities = clubActivitySummaryMapper.getActivitiesForClubAndMonth(club.getClubId(),currentYear,i);
                activitiesByMonths.put(i+"月",activities);
            }

            activitiesByMonth.setActivities(activitiesByMonths);
            clubActivities.put(club.getClubName(),activitiesByMonth);
        }
        summaryVO.setActivities(clubActivities);


        //3.获取所管的社团的id和社团name，然后返回这一个社团与前面填充的月份列表的活动的数量总数
        // 遍历每个社团，计算每个社团在每个月份的活动数量总数
        HashMap<String, List<Integer>> clubActivitiesTotals = new HashMap<>();
        for (Club club : clubs) {
            List<Integer> activitiesByMonth = new ArrayList<>();
            for (int i = 1; i <= currentMonth.getValue(); i++) {
                int totalActivities = clubActivitySummaryMapper.getTotalActivitiesForClubAndMonth(club.getClubId(),currentYear,i);
                activitiesByMonth.add(totalActivities);
            }
            clubActivitiesTotals.put(club.getClubName(),activitiesByMonth);
        }
        summaryVO.setActivityCounts(clubActivitiesTotals);

        return summaryVO;
    }
}
