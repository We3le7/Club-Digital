package com.club.service.impl;

import com.club.dto.ManagerPageQueryDTO;
import com.club.dto.RoleDTO;
import com.club.exception.BaseException;
import com.club.mapper.StudentInfoMapper;
import com.club.result.PageResult;
import com.club.service.ManagerManagementService;
import com.club.vo.ApplyPageVO;
import com.club.vo.ManagerInfoVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ManagerManagementServiceImpl implements ManagerManagementService {
    @Autowired
    private StudentInfoMapper managerInfoMapper;

    /**
     * 社管查询
     * @param managerPageQueryDTO
     * @return
     */
    public PageResult pageQuery(ManagerPageQueryDTO managerPageQueryDTO){
        PageHelper.startPage(managerPageQueryDTO.getPage(),managerPageQueryDTO.getPageSize());
        Page<ManagerInfoVO> page = managerInfoMapper.getManagerPageQuery(managerPageQueryDTO);
        return new PageResult(page.getTotal(),page.getResult());
    }


    public void updateManagerRole(RoleDTO roleDTO){
        Integer role = 3;
        if(roleDTO.getOccupation().equals("社长")){
            role=1;
        }else if(roleDTO.getOccupation().equals("社管")){
            role=2;
        }
        managerInfoMapper.updateManagerRole(roleDTO.getClubId(),roleDTO.getStudentId(),role);
    }
}
