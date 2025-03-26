package com.club.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.club.entity.Membership;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MembershipMapper extends BaseMapper<Membership> {
    @Select("select * from membership where club_id=#{clubId} and role=1")
    Membership getMembershipByIdAndRole(Integer clubId);

    @Insert("insert into membership(club_id,student_id,is_belong,role) values(#{clubId},#{studentId},#{isBelong},#{role})")
    int insert(Membership membership);

    @Update("update membership set is_belong=#{isBelong} where club_id=#{clubId} and student_id=#{studentId}")
    void quitClub(Integer studentId,Integer clubId, Boolean isBelong);
    @Select("select club_id from membership where student_id=#{currentId}")
    Integer getClubIdByStudentId(Integer currentId);
}
