package com.club.service;

import com.club.result.PageResult;

public interface ActivityLeagueService {
    /***
     * 校团委端获取所有的活动模板
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult getAllTemplate(Integer pageNum, Integer pageSize);

    /***
     * 校团委端添加活动模板
     * @param templateName
     * @param filePath
     * @param templateType
     */
    void addTemplate(String templateName, String filePath, Integer templateType);

    /***
     * 校团委端删除活动模板
     * @param templateId
     */
    void deleteTemplate(Integer templateId);
}
