package com.club.mapper;

import com.club.dto.ClubInfoUserDTO;
import com.club.vo.ClubInfoUserVO;
import com.github.pagehelper.Page;
import io.swagger.util.Json;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ConfigMapper {

    //kind为json类型
    @Select("select kind from config_info where name = #{name}")
    String getConfig(String name);
}
