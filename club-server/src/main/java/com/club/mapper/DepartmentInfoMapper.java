package com.club.mapper;

import com.club.entity.DepartmentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface DepartmentInfoMapper {
    @Select("select * from department_info where department_account = #{departmentAccount}")
    DepartmentInfo getByAccount(String account);
    @Select("select * from department_info where department_id = #{account}")
    DepartmentInfo getById(Integer account);
}
