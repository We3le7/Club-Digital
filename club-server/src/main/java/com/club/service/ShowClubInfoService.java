package com.club.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.club.entity.StudentInfo;
import com.club.result.PageResult;
import com.club.vo.ClubInfoStaticVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShowClubInfoService  {

    List<ClubInfoStaticVO> showClubInfo(Integer departmentId);
}
