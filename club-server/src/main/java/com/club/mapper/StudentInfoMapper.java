package com.club.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.club.dto.ManagerPageQueryDTO;
import com.club.entity.StudentInfo;
import com.club.result.PageResult;
import com.club.vo.ClubQuitInfoVO;
import com.club.vo.ManagerInfoVO;
import com.club.vo.StudentInfoVO;
import com.club.vo.UserLoginVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface StudentInfoMapper extends BaseMapper<StudentInfo> {
//test
    @Select("select * from student_info,membership where club_id=#{clubId} and student_info.student_id=membership.student_id")
    Page<StudentInfo> getAllMember(Integer clubId);

    @Select("select * from student_info where student_num=#{studentNum}")
    StudentInfo getByStudentNum(Integer studentNum);

    @Select("select applicant_id as student_id, applicant_name as student_name,applicant_id as student_num,apply_time,apply_id from application_quit where club_id=#{clubId} and state=0")
    Page<ClubQuitInfoVO> getQuitMember(Integer clubId);
    @Select("select * from student_info where email = #{email}")
    StudentInfo getByEmail(String email);

    @Select("select * from student_info where student_id = #{studentId}")
    StudentInfo getById(Integer studentId);

    @Insert("insert into student_info(student_num,student_name,email,phone_num,sex,college,grade,password_hash,level,student_photo_url) " +
            "values" +
            "(#{studentNum},#{studentName},#{email},#{phoneNum},#{sex},#{college},#{grade},#{passwordHash},#{level},#{studentPhotoUrl})")
    int insert(StudentInfo studentInfo);

    int updateById(StudentInfo studentInfo);
    @Update("update student_info set password_hash=#{passwordHash} where email=#{email}")
    void updateByEmail(StudentInfo studentInfo);

    @Select("select student_name from student_info where email=#{email}")
    String getStudentName(String email);

    Page<ManagerInfoVO> getManagerPageQuery(ManagerPageQueryDTO managerPageQueryDTO);

    void updateManagerRole(@Param(value = "clubId") Integer clubId,@Param(value = "studentId") Integer studentId,@Param(value = "role") Integer role);

    @Select("select * from student_info where student_name=#{studentName}")
    StudentInfo getByStudentName(String studentName);

    UserLoginVO getLoginById(Integer studentId);
}
