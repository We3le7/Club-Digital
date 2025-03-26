package com.club.mapper;

import com.club.dto.ActivityOverviewUserDTO;
import com.club.entity.ApplicationQuit;
import com.club.vo.ActivityOverviewUserVO;
import com.club.vo.MyApplicationVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ApplicationQuitMapper extends com.baomidou.mybatisplus.core.mapper.BaseMapper<ActivityOverviewUserDTO> {

    @Update("update application_quit set state = #{state} where apply_id = #{applyId}")
    void updateState(Integer applyId, boolean state);

    @Select("select * from application_quit where apply_id = #{applyId}")
    ApplicationQuit searchStudentId(Integer applyId);

    List<MyApplicationVO> myApplication(Integer applicantId);
}
