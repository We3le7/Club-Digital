package com.club.controller.league;

import com.club.dto.RecommendResultDTO;
import com.club.result.PageResult;
import com.club.result.Result;
import com.club.service.PromoteService;
import com.club.vo.AllRecommendResultVO;
import com.club.vo.ClubFilesVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin/league/promote")
public class PromoteAdminController {
    @Autowired private PromoteService promoteService;
    /**
     * 单个社团推优处理
     * @param club_id
     * @return
     */
    @PostMapping
    public Result<String> Promote(@ModelAttribute RecommendResultDTO recommendResultDTO) {
        log.error("推优处理：{}", recommendResultDTO);
        promoteService.Promote(recommendResultDTO);
        return Result.success();
    }
    /**
     * 获取所有社团推优结果
     * @param club_id
     * @return
     */
    @GetMapping("/list")
    public Result<PageResult> getPromote(String clubName, Integer pageNum, Integer pageSize) {
        //使用mybatisplus
        PageResult allRecommendResultVO=promoteService.getAllPromote(clubName, pageNum, pageSize);
        return Result.success(allRecommendResultVO);
    }
    @GetMapping("/files")
    @ApiOperation("查看已上传评优文件")
    public Result<List<ClubFilesVO>> getExaminationFile(Integer clubId) {
        List<ClubFilesVO> list=promoteService.getPromoteFile(clubId);
        return Result.success(list);
    }

}
