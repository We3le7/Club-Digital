package com.club.service;

import com.club.dto.*;
import com.club.result.PageResult;
import com.club.vo.RecruitDetailVO;

public interface RecruitService {
    /**
     * 招新详情
     * @param recruitId
     * @return
     */
    RecruitDetailVO getRecruitDetail(Integer recruitId);
    /**
     * 申请加入社团
     * @param applyClubDTO
     * @return
     */
    void applyClub(ApplyClubDTO applyClubDTO);
    /**
     * 招新分页查询
     */
    PageResult pageQuery(RecruitPageQueryDTO recruitPageQueryDTO);
    /**
     * 发布招新
     * @param publishRecruitDTO
     * @return
     */
    void publishRecruit(PublishRecruitDTO publishRecruitDTO);
    /**
     * 批准加入
     * @param applyId
     * @return
     */
    void approve(Integer applyId);
    /**
     * 拒绝加入
     * @param applyId
     * @return
     */
    void reject(Integer applyId);

    /**
     * 查看所有招新
     * @param applyPageDTO
     * @return
     */
    PageResult getAllApplication(ApplyPageDTO applyPageDTO);

    void interview(InterviewDTO interviewDTO);
}
