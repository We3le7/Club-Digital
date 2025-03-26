package com.club.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.club.dto.MyClubDTO;
import com.club.dto.RecruitPageQueryDTO;
import com.club.entity.ClubInfo;
import com.club.vo.ClubInfoVO;
import com.club.vo.MyClubPageVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ClubInfoMapper extends BaseMapper<ClubInfo> {
    @Select("select * from club_info where club_id=#{clubId}")
    ClubInfo getClubInfoById(Integer clubId);

    Page<ClubInfoVO> pageQuery(RecruitPageQueryDTO recruitPageQueryDTO);
    Page<MyClubPageVO> pageMyClubQuery(MyClubDTO myClubDTO);
}
