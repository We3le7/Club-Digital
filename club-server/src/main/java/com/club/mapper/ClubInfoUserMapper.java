package com.club.mapper;

import com.club.dto.ClubInfoUserDTO;
import com.club.vo.ClubInfoUserVO;
import com.club.vo.ClubInfoVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClubInfoUserMapper {
    /***
     * 社团分页查询
     * @param clubInfoUserDTO
     * @return
     */
    Page<ClubInfoUserVO> pageQuery(ClubInfoUserDTO clubInfoUserDTO);

    /***
     * 根据id查询出该社团的所有url
     * @param clubId
     * @return
     */
    List<String> getUrlByClubId(Integer clubId);

    /***
     * 根据id查询社团的详细信息
     * @param clubId
     * @return
     */
    ClubInfoUserVO clubInfoGetById(Integer clubId);
}
