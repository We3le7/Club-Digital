package com.club.mapper;

import com.club.dto.FileUploadDTO;
import com.club.entity.Recommend;
import com.club.vo.AllAuditResultVO;
import com.club.vo.AllRecommendResultVO;
import com.club.vo.ClubFilesVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface PromoteMapper {
   
    @Select("select * from recommend where recommend_id=#{id}")
    Recommend getPromote(Integer id);

    @Select("select recommend_file as url from recommend_files where club_id=#{clubId} ")
    List<ClubFilesVO> getPromoteFile(Integer clubId);

    @Insert("update recommend set recommend_time=#{recommendTime},is_excellent=#{isExcellent},examine_feedback=#{examineFeedback},examiner_id=#{examinerId},examiner_name=#{examinerName},recommend_title=#{recommendTitle} where club_id=#{clubId}")
    void Promote(Recommend recommend);

    Page<AllRecommendResultVO> getAllPromote(String clubName);

    void uploadPromoteFile(List<FileUploadDTO.FileUrl> fileUrls, Integer clubId);
}
