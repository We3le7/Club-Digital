package com.club.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.club.entity.ApplicationQuit;
import com.club.entity.StudentInfo;
import com.club.mapper.ApplicationQuitMapper;
import com.club.mapper.MembershipMapper;
import com.club.mapper.StudentInfoMapper;
import com.club.result.PageResult;
import com.club.service.MemberManagementService;
import com.club.vo.ClubQuitInfoVO;
import com.club.vo.StudentInfoVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberManagementServiceImpl extends ServiceImpl<StudentInfoMapper, StudentInfo> implements MemberManagementService  {
    @Autowired
    StudentInfoMapper studentInfoMapper;
    @Autowired
    ApplicationQuitMapper ApplicationQuitMapper;
    @Autowired
    MembershipMapper MembershipMapper;
    @Override
    public PageResult getAllMember(Integer clubId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<StudentInfo> page = studentInfoMapper.getAllMember(clubId);
        return new PageResult(page.getTotal(), page.getResult());

    }

    @Override
    public StudentInfo getByStudentNum(Integer studentNum) {
        return studentInfoMapper.getByStudentNum(studentNum);
    }

    @Override
    public PageResult getQuitMember(Integer clubId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<ClubQuitInfoVO> page = studentInfoMapper.getQuitMember(clubId);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 退社处理
     */
    @Override
    public void quitMember(Integer applyId, Boolean state) {
        //如果state=1，则说明批准退社，将is_belong改为false
        if (state) {
            ApplicationQuitMapper.updateState(applyId, true);
            //从退社申请表中查找学生id和社团id
            ApplicationQuit applicationQuit =ApplicationQuitMapper.searchStudentId(applyId);
            //将社团表对应id的is_belong改为false
            MembershipMapper.quitClub(applicationQuit.getApplicantId(),applicationQuit.getClubId(), false);
        }
        //如果state=0，则说明拒绝退社，将is_belong改为true
        else {
            ApplicationQuitMapper.updateState(applyId, false);
            ApplicationQuit applicationQuit =ApplicationQuitMapper.searchStudentId(applyId);
            MembershipMapper.quitClub(applicationQuit.getApplicantId(),applicationQuit.getClubId(), true);
        }

    }

    @Override
    public StudentInfo getByStudentName(String studentName) {
        return studentInfoMapper.getByStudentName(studentName);
    }
}
