package com.club.mapper;

import com.club.vo.ActivityVO;
import com.club.vo.Club;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClubActivitySummaryMapper {

    //根据departmentId获取所管的社团的Id和name
    @Select("select club_id,club_name from club_info where department_id = #{departmentId}")
    List<Club> getClubsByDepartmentId(Integer departmentId);

    //需要根据当前的clubId和月份month，到activity中查找有多少条数据是符合要求的
    @Select("SELECT COUNT(*) FROM activity WHERE club_id = #{clubId} AND YEAR(start_datetime) = #{year} AND MONTH(start_datetime) = #{month}")
    int getTotalActivitiesForClubAndMonth(Integer clubId,Integer year,Integer month);

    //需要根据当前的clubId和年份year和月份month，到activity中查找ActivityVO数据
    @Select("SELECT activity_id,title from activity where club_id=#{clubId} AND YEAR(start_datetime) = #{year} AND MONTH(start_datetime) = #{month}")
    List<ActivityVO> getActivitiesForClubAndMonth(Integer clubId, Integer year, Integer month);
}
