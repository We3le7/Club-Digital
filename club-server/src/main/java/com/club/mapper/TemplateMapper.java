package com.club.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.club.entity.Recruitment;
import com.club.entity.StudentInfo;
import com.club.vo.TemplateDownloadVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TemplateMapper extends BaseMapper<Recruitment> {

    @Select("select * from template where template_type = #{type}")
    List<TemplateDownloadVO> getTemplate(Integer type);
}
