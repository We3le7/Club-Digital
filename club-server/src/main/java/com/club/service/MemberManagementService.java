package com.club.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.club.entity.StudentInfo;
import com.club.result.PageResult;
import org.springframework.stereotype.Service;

@Service
public interface MemberManagementService extends IService<StudentInfo> {
    /**
     * 获取所有社团成员
     * @return
     */
    PageResult getAllMember(Integer clubId, Integer pageNum, Integer pageSize);

    StudentInfo getByStudentNum(Integer studentNum);

    PageResult getQuitMember(Integer clubId, Integer pageNum, Integer pageSize);

    void quitMember(Integer applyId, Boolean state);

    StudentInfo getByStudentName(String studentName);
}
