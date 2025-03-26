package com.club.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.club.dto.ApplyPageDTO;
import com.club.dto.MyApplicationDTO;
import com.club.entity.ApplicationJoin;
import com.club.vo.ApplyPageVO;
import com.club.vo.MyApplicationVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ApplicationJoinMapper extends BaseMapper<ApplicationJoin> {

    Page<ApplyPageVO> getPageApplication(ApplyPageDTO applyPageDTO);

    @Insert("INSERT INTO application_join (applicant_name, apply_time, applicant_id, club_id, recruit_id) " +
            "VALUES " +
            "(#{applicantName}, #{applyTime}, #{applicantId}, #{clubId}, #{recruitId})")
    int insert(ApplicationJoin applicationJoin);

    void approve(Integer studentNum);

    void update(ApplicationJoin applicationJoin);
    @Select("SELECT * FROM application_join WHERE apply_id = #{applyId}")
    ApplicationJoin getById(Integer applyId);

    List<MyApplicationVO> myApplication(Integer applicantId);

    List getByClubId(Integer clubId);
}
