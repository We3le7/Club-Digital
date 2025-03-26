package com.club.service.impl;

import com.club.dto.FileUploadDTO;
import com.club.dto.RecommendResultDTO;
import com.club.entity.Recommend;
import com.club.mapper.PromoteMapper;
import com.club.result.PageResult;
import com.club.service.PromoteService;
import com.club.utils.AliOssUtil;
import com.club.vo.AllRecommendResultVO;
import com.club.vo.ClubFilesVO;
import com.club.vo.RecommendResultVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PromoteServiceImpl implements PromoteService {
    @Autowired
    private PromoteMapper promoteMapper;
    @Autowired
    private AliOssUtil aliOssUtil;
    public RecommendResultVO getPromote(Integer club_id) {
        Recommend recommend=promoteMapper.getPromote(club_id);
        RecommendResultVO recommendResultVO=new RecommendResultVO();
        BeanUtils.copyProperties(recommend,recommendResultVO);
        return recommendResultVO;
    }

    public List<ClubFilesVO> getPromoteFile(Integer club_id) {
        return promoteMapper.getPromoteFile(club_id);
    }

    @Override
    public void Promote(RecommendResultDTO recommendResultVO) {
        Recommend recommend=new Recommend();
        BeanUtils.copyProperties(recommendResultVO,recommend);
        //设置时间为当前时间
        recommend.setRecommendTime(LocalDateTime.now());
        promoteMapper.Promote(recommend);
    }

    @Override
    public PageResult getAllPromote(String clubName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<AllRecommendResultVO> page=promoteMapper.getAllPromote(clubName);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void uploadPromoteFile(List<FileUploadDTO.FileUrl> fileUrls, Integer clubId) {
        promoteMapper.uploadPromoteFile(fileUrls,clubId);
    }


}
