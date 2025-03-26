package com.club.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.club.entity.Recruitment;
import com.club.entity.StudentInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RecruitmentMapper extends BaseMapper<Recruitment> {
    @Select("select * from recruitment where recruit_id=#{recruitId}")
    Recruitment getRecruitmentById(Integer recruitId);
    @Insert("insert into recruitment(club_id,start_time,end_time,introduction,photo_url) " +
            "values" +
            "(#{clubId},#{startTime},#{endTime},#{introduction},#{photoUrl})")
    int insert(StudentInfo studentInfo);
}
