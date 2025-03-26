package com.club.service.impl;

import com.club.dto.AuditResultDTO;
import com.club.dto.FileUploadDTO;
import com.club.entity.Audit;
import com.club.mapper.ExaminationMapper;
import com.club.result.PageResult;
import com.club.service.ExaminationService;
import com.club.utils.AliOssUtil;
import com.club.vo.AllAuditResultVO;
import com.club.vo.AuditResultVO;
import com.club.vo.ClubFilesVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Slf4j
@Service
public class ExaminationServiceImpl implements ExaminationService {
    @Autowired
    private ExaminationMapper examinationMapper;

    @Autowired
    private AliOssUtil aliOssUtil;

    @Override
    public AuditResultVO getExamination(Integer id) {
        Audit audit=examinationMapper.getExamination(id);
        AuditResultVO auditResultVO=new AuditResultVO();
        BeanUtils.copyProperties(audit,auditResultVO);
        return auditResultVO;
    }

    @Override
    public void audit(AuditResultDTO auditResultDTO) {
        Audit audit=new Audit();
        BeanUtils.copyProperties(auditResultDTO,audit);
        audit.setAuditTime(LocalDateTime.now());
        examinationMapper.audit(audit);
    }

    @Override
    public PageResult getAllAudit(String clubName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<AllAuditResultVO>page=examinationMapper.getAllAudit(clubName);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public List<ClubFilesVO> getExaminationFile(Integer clubId) {
        return examinationMapper.getExaminationFile(clubId);
    }

    @Override
    public void uploadExaminationFiles(List<FileUploadDTO.FileUrl> fileUrls, Integer clubId) {
        examinationMapper.uploadExaminationFiles(fileUrls,clubId);

    }


}
