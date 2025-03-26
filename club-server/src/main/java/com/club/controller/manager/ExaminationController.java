package com.club.controller.manager;

import com.club.dto.FileUploadDTO;
import com.club.result.Result;
import com.club.service.ExaminationService;
import com.club.vo.AuditResultVO;
import com.club.vo.ClubFilesVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user/manager/examination")
@Api(tags = "年审接口")
public class ExaminationController {
    @Autowired private ExaminationService examinationService;
    /**
     * 获取年审结果
     * @param clubId
     * @return
     */
    @GetMapping()
    @ApiOperation("获取年审结果")
    public Result<AuditResultVO> getExamination(Integer clubId) {
        AuditResultVO auditResultVO=examinationService.getExamination(clubId);
        return Result.success(auditResultVO);
    }
    /**
     *上传年审文件
     * @param club_id
     * @return
     */
    /**
     * 上传推优文件接口
     *
     *
     * @param request 请求对象
     * @return JSON响应对象
     */
    @PostMapping("/upload")
    @ApiOperation("上传年审文件")
    public Result uploadRecommendationFiles(@RequestBody FileUploadDTO request){
        examinationService.uploadExaminationFiles(request.getFileUrls(), request.getClubId());
        return Result.success();
    }

    /**
     *查看已上传年审文件
     * @param clubId
     * @return
     */
    @GetMapping("/files")
    @ApiOperation("查看已上传年审文件")
    public Result<List<ClubFilesVO>> getExaminationFile(Integer clubId) {
        List<ClubFilesVO> list=examinationService.getExaminationFile(clubId);
        return Result.success(list);
    }
}
