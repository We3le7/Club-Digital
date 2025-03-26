package com.club.service;

import com.club.dto.ClubInfoManagerDTO;
import com.club.dto.ClubInfoUserDTO;
import com.club.result.Result;
import com.club.vo.ActivityVO;

import java.util.List;

//社团管理
public interface ClubManagerService {

    /***
     * 修改社团相关信息
     * @param clubInfoManagerDTO
     */
    void updateClubInfo(ClubInfoManagerDTO clubInfoManagerDTO);

    /***
     * 获取社团的活动的相关信息
     * @param clubId
     * @return
     */
    List<ActivityVO> getActivityByClubId(Integer clubId);
}
