package com.club.controller.manager;

import com.club.dto.FileUploadDTO;
import com.club.result.Result;
import com.club.service.PromoteService;
import com.club.service.impl.PromoteServiceImpl;
import com.club.vo.AuditResultVO;
import com.club.vo.ClubFilesVO;
import com.club.vo.RecommendResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/manager/promote")
@Api(tags = "评优接口")
public class PromoteController {
    @Autowired private PromoteService promoteService;
    /**
     * 获取结果
     * @param club_id
     * @return
     */
    @GetMapping()
    @ApiOperation("获取评优结果")
    public Result<RecommendResultVO> getPromote(Integer clubId) {
        RecommendResultVO recommendResultVO=promoteService.getPromote(clubId);
        return Result.success(recommendResultVO);
    }
    /**
     *上传评优文件
     * @param club_id
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("上传评优文件")
    public Result uploadRecommendFiles(@RequestBody FileUploadDTO request){
        promoteService.uploadPromoteFile(request.getFileUrls(), request.getClubId());
        return Result.success();
    }
    /**
     *查看已上传评优文件
     * @param club_id
     * @return
     */
    @GetMapping("/files")
    @ApiOperation("查看已上传评优文件")
    public Result<List<ClubFilesVO>> getExaminationFile(Integer clubId) {
        List<ClubFilesVO> list=promoteService.getPromoteFile(clubId);
        return Result.success(list);
    }

}
