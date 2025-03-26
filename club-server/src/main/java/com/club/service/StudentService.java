package com.club.service;

import com.club.dto.*;
import com.club.entity.StudentInfo;
import com.club.result.PageResult;
import com.club.vo.ApplyPageVO;
import com.club.vo.MyApplicationVO;
import com.club.vo.UserLoginVO;

import java.util.List;

public interface StudentService {

    /**
     * 登录
     * @param userLoginDTO
     * @return
     */
    UserLoginVO login(UserLoginDTO userLoginDTO);
    /**
     * 查询个人信息
     * @param studentId
     * @return
     */

    UserRegisterDTO getById(Integer studentId);
    /**
     * 注册
     * @param userRegisterDTO
     * @return
     */
    void register(UserRegisterDTO userRegisterDTO);
    /**
     * 修改个人信息
     * @param studentId
     * @param userRegisterDTO
     * @return
     */
    void updateById(Integer studentId, UserRegisterDTO userRegisterDTO);

    void getCode(String email,String subject,String context);
    Integer checkCode(String email,String code);

    void findPassword(FindPasswordDTO findPasswordDTO);

    PageResult pageQuery(MyClubDTO myClubDTO);

    List<MyApplicationVO> myApplication(Integer applicantId);
}
