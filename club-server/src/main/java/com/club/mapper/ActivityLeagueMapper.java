package com.club.mapper;

import com.club.vo.TemplateInfoVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityLeagueMapper {

    /***
     * 校团委端获取所有的活动模板
     * @return
     */
    Page<TemplateInfoVO> getAllTemplate();

    /***
     * 校团委端添加活动模板
     * @param templateName
     * @param filePath
     * @param templateType
     */
    @Insert("insert into template(template_name,template_url,template_type) values(#{templateName},#{filePath},#{templateType})")
    void addTemplate(String templateName, String filePath, Integer templateType);

    /***
     * 校团委端删除活动模板
     * @param templateId
     */
    @Delete("delete from template where template_id=#{templateId}")
    void deleteTemplate(Integer templateId);
}
