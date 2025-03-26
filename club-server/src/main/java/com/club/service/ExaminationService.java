package com.club.service;

import com.club.dto.AuditResultDTO;
import com.club.dto.FileUploadDTO;
import com.club.result.PageResult;
import com.club.vo.AllAuditResultVO;
import com.club.vo.AuditResultVO;
import com.club.vo.ClubFilesVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExaminationService {
    AuditResultVO getExamination(Integer id);


    void audit(AuditResultDTO auditResultDTO);

    PageResult getAllAudit(String clubName, Integer pageNum, Integer pageSize);

    List<ClubFilesVO> getExaminationFile(Integer clubId);

    void uploadExaminationFiles(List<FileUploadDTO.FileUrl> fileUrls, Integer clubId);
}
