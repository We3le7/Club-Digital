package com.club.service.impl;

import com.club.mapper.TemplateMapper;
import com.club.service.TemplateService;
import com.club.vo.TemplateDownloadVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TemplateServiceImpl implements TemplateService {
    @Autowired private TemplateMapper templateMapper;
    @Override
    public List<TemplateDownloadVO> getTemplate(Integer type) {
        return templateMapper.getTemplate(type);
    }
}
