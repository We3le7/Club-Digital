package com.club.service.impl;

import com.club.dto.ClubInfoUserDTO;
import com.club.mapper.ClubInfoUserMapper;
import com.club.result.PageResult;
import com.club.service.ClubUserService;
import com.club.vo.ClubInfoUserVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ClubUserServiceImpl implements ClubUserService {
    @Autowired
    private ClubInfoUserMapper clubInfoUserMapper;
    /***
     * 社团分页查询
     * @param clubInfoUserDTO
     * @return
     */
    @Override
    public PageResult pageQuery(ClubInfoUserDTO clubInfoUserDTO) {
        PageHelper.startPage(clubInfoUserDTO.getPageNum(),clubInfoUserDTO.getPageSize());
        //只是获取了部分数据
        Page<ClubInfoUserVO> page=clubInfoUserMapper.pageQuery(clubInfoUserDTO);
        // 遍历部分数据，调用getUrlByClubId方法获取照片URL，并设置clubPhotoUrls
        for (ClubInfoUserVO clubInfoUserVO : page.getResult()) {
            List<String> clubPhotoUrls = clubInfoUserMapper.getUrlByClubId(clubInfoUserVO.getClubId());
            clubInfoUserVO.setClubPhotoUrls(clubPhotoUrls);
        }
        return new PageResult(page.getTotal(),page.getResult());
    }

    /***
     * 根据id查询社团的详细信息
     * @param clubId
     * @return
     */
    @Override
    public ClubInfoUserVO clubInfoGetById(Integer clubId) {
        //根据id查询出当前id对应的所有url并且放在List中
        List<String> urlList=clubInfoUserMapper.getUrlByClubId(clubId);
        
        //根据id查询出其它的信息
        ClubInfoUserVO clubInfoUserVO=clubInfoUserMapper.clubInfoGetById(clubId);

        //将url放在vo中
        clubInfoUserVO.setClubPhotoUrls(urlList);

        //讲clubId设置在vo中
        clubInfoUserVO.setClubId(clubId);

        return clubInfoUserVO;
    }
}
