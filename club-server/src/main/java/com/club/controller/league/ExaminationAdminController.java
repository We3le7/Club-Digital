package com.club.controller.league;

import com.club.dto.AuditResultDTO;
import com.club.result.PageResult;
import com.club.result.Result;
import com.club.service.ExaminationService;
import com.club.vo.AllAuditResultVO;
import com.club.vo.ClubFilesVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/league/examination")
public class ExaminationAdminController {
    @Autowired
    private ExaminationService examinationService;

    /**
     * 单个社团年审处理
     * @param auditResultDTO
     * @return
     */
    @PostMapping
    public Result<String> audit(@ModelAttribute AuditResultDTO auditResultDTO) {
        examinationService.audit(auditResultDTO);
        return Result.success();
    }

    /**
     * 查看所有审核结果
     * @return
     */
       @GetMapping("/list")
    public Result<PageResult> getAudit(String clubName, Integer pageNum, Integer pageSize) {
        PageResult allAuditResultVO = examinationService.getAllAudit(clubName, pageNum, pageSize);
        return Result.success(allAuditResultVO);
    }
    /**
     *查看已上传年审文件
     * @return
     */
    @GetMapping("/files")
    @ApiOperation("查看已上传年审文件")
    public Result<List<ClubFilesVO>> getExaminationFile(Integer clubId) {
        List<ClubFilesVO> list=examinationService.getExaminationFile(clubId);
        return Result.success(list);
    }

}
