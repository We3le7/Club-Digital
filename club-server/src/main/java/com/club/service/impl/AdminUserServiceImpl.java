package com.club.service.impl;

import com.club.constant.MessageConstant;
import com.club.dto.AdminUserLoginDTO;
import com.club.entity.DepartmentInfo;
import com.club.entity.StudentInfo;
import com.club.exception.AccountNotFoundException;
import com.club.exception.PasswordErrorException;
import com.club.mapper.DepartmentInfoMapper;
import com.club.service.AdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
@Service
@Slf4j
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    DepartmentInfoMapper departmentInfoMapper;
    public DepartmentInfo login(AdminUserLoginDTO adminUserLoginDTO){
        String account = adminUserLoginDTO.getDepartmentAccount();
        String password = adminUserLoginDTO.getPassword();

        //1、根据邮箱查询数据库中的数据
        DepartmentInfo departmentInfo = departmentInfoMapper.getByAccount(account);

        //2、判断数据库中的数据是否为空
        if(departmentInfo == null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //3、判断密码是否正确
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if(!password.equals(departmentInfo.getPassword())){
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        //4、返回用户信息
        return departmentInfo;
    }
    public DepartmentInfo getInformation(Integer account){
        DepartmentInfo departmentInfo = departmentInfoMapper.getById(account);
        return departmentInfo;
    }
}
