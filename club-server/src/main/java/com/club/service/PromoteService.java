package com.club.service;

import com.club.dto.FileUploadDTO;
import com.club.dto.RecommendResultDTO;
import com.club.result.PageResult;
import com.club.vo.AllRecommendResultVO;
import com.club.vo.ClubFilesVO;
import com.club.vo.RecommendResultVO;

import java.util.List;
import java.util.Map;

public interface PromoteService {
    RecommendResultVO getPromote(Integer clubId);

    List<ClubFilesVO> getPromoteFile(Integer clubId);

    void Promote(RecommendResultDTO recommendResultVO);

    PageResult getAllPromote(String clubName, Integer pageNum, Integer pageSize);

    void uploadPromoteFile(List<FileUploadDTO.FileUrl> fileUrls, Integer clubId);
}