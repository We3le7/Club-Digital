package com.club.service.impl;

import com.club.mapper.ShowClubInfoMapper;
import com.club.service.ShowClubInfoService;
import com.club.vo.ClubInfoStaticVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowClubInfoServiceImpl implements ShowClubInfoService {

    @Autowired
    private ShowClubInfoMapper showClubInfoMapper;
    @Override
    public List<ClubInfoStaticVO> showClubInfo(Integer departmentId) {
        List<ClubInfoStaticVO> clubInfoStaticVO=showClubInfoMapper.showClubInfo(departmentId);
        clubInfoStaticVO.forEach(item->{
            Integer undergradnum=showClubInfoMapper.countUndergradNum(item.getClubId());
            item.setUndergrad(undergradnum);
            Integer gradnum=showClubInfoMapper.countGradNum(item.getClubId());
            item.setGrad(gradnum);
            Integer malenum=showClubInfoMapper.countMaleNum(item.getClubId());
            item.setMale(malenum);
            Integer femalenum=showClubInfoMapper.countFemaleNum(item.getClubId());
            item.setFemale(femalenum);
        });
        return clubInfoStaticVO;
    }
}
