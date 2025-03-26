package com.club.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.club.dto.FileUploadDTO;
import com.club.entity.Audit;
import com.club.vo.AllAuditResultVO;
import com.club.vo.ClubFilesVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import java.util.List;

@Mapper
public interface ExaminationMapper{
    @Select("select * from audit where audit_id=#{id}")
    Audit getExamination(Integer id);

    @Select("select audit_file as url from audit_files where club_id=#{clubId} ")
    List<ClubFilesVO> getExaminationFile(Integer clubId);

   // @Insert("insert into audit(audit_id, audit_time, club_id, is_pass, examiner_id, examiner_name, examine_feedback) values(#{auditId}, #{auditTime}, #{clubId}, #{isPass}, #{examinerId}, #{examinerName}, #{examineFeedback})")
    @Update("update audit set audit_time=#{auditTime},is_pass=#{isPass},examiner_id=#{examinerId},examiner_name=#{examinerName},examine_feedback=#{examineFeedback} where club_id=#{clubId}")
    void audit(Audit audit);


    Page<AllAuditResultVO> getAllAudit(String clubName);

    void uploadExaminationFiles(List<FileUploadDTO.FileUrl> fileUrls, Integer clubId);
}
