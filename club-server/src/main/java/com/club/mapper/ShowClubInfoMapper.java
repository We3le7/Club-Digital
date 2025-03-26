package com.club.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.club.entity.Recruitment;
import com.club.vo.ClubInfoStaticVO;
import com.club.vo.TemplateDownloadVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShowClubInfoMapper extends BaseMapper<Recruitment> {

    @Select("select club_id,club_name,type_id as type_name,district as campus_name from club_info where department_id=#{departmentId}")
    List<ClubInfoStaticVO> showClubInfo(Integer departmentId);

    @Select("select count(*) from student_info,membership where club_id=#{clubId} and student_info.student_id=membership.student_id and level=1")
    Integer countUndergradNum(Integer clubId);

    @Select("select count(*) from student_info,membership where club_id=#{clubId} and student_info.student_id=membership.student_id and level=2")
    Integer countGradNum(Integer clubId);

    @Select("select count(*) from student_info,membership where club_id=#{clubId} and student_info.student_id=membership.student_id and sex=1")
    Integer countMaleNum(Integer clubId);

    @Select("select count(*) from student_info,membership where club_id=#{clubId} and student_info.student_id=membership.student_id and sex=0")
    Integer countFemaleNum(Integer clubId);
}
