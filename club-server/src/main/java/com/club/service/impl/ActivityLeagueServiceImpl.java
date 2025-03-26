package com.club.service.impl;

import com.club.mapper.ActivityLeagueMapper;
import com.club.result.PageResult;
import com.club.service.ActivityLeagueService;
import com.club.vo.TemplateInfoVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityLeagueServiceImpl implements ActivityLeagueService {
    @Autowired
    private ActivityLeagueMapper  activityLeagueMapper;


    /***
     * 校团委端获取所有的活动模板
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageResult getAllTemplate(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<TemplateInfoVO>  templateInfoPage = activityLeagueMapper.getAllTemplate();
        return new PageResult(templateInfoPage.getTotal(), templateInfoPage.getResult());
    }

    /***
     * 校团委端添加活动模板
     * @param templateName
     * @param filePath
     * @param templateType
     */
    @Override
    public void addTemplate(String templateName, String filePath, Integer templateType) {
        //直接将参数插入到template表格中
        activityLeagueMapper.addTemplate(templateName,filePath,templateType);
    }

    /***
     * 校团委端删除活动模板
     * @param templateId
     */
    @Override
    public void deleteTemplate(Integer templateId) {
        //直接通过模板Id删除对应的模板数据
        activityLeagueMapper.deleteTemplate(templateId);
    }
}
