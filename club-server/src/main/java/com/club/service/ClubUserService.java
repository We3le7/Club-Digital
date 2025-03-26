package com.club.service;

import com.club.dto.ClubInfoUserDTO;
import com.club.result.PageResult;
import com.club.vo.ClubInfoUserVO;

public interface ClubUserService {
    /***
     * 社团分页查询
     * @param clubInfoUserDTO
     * @return
     */
    PageResult pageQuery(ClubInfoUserDTO clubInfoUserDTO);

    /***
     * 根据id查询社团的详细信息
     * @param clubId
     * @return
     */
    ClubInfoUserVO clubInfoGetById(Integer clubId);
}
