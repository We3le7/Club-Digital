package com.club.service;

import com.club.dto.FileUploadDTO;
import com.club.dto.RecommendResultDTO;
import com.club.result.PageResult;
import com.club.vo.ClubFilesVO;
import com.club.vo.RecommendResultVO;
import com.club.vo.TemplateDownloadVO;

import java.util.List;

public interface TemplateService {

    List<TemplateDownloadVO> getTemplate(Integer type);
}