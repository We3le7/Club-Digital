package com.club.controller.user;

import com.club.dto.ApplyClubDTO;
import com.club.dto.RecruitPageQueryDTO;
import com.club.result.PageResult;
import com.club.result.Result;
import com.club.service.RecruitService;
import com.club.vo.RecruitDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/recruit")
@Slf4j
@Api(tags = "用户端-招新")
public class UserRecruitController {
    @Autowired
    private RecruitService recruitService;
    /**
     * 招新分页查询
     */
    @GetMapping(value = "/page")
    @ApiOperation(value = "招新分页查询")
    public Result<PageResult> page(RecruitPageQueryDTO recruitPageQueryDTO){
        log.info("招新分页查询：{}", recruitPageQueryDTO);
        PageResult pageResult = recruitService.pageQuery(recruitPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 招新详情
     * @param recruitId
     * @return
     */

    @GetMapping
    @ApiOperation(value = "招新详情")
    public Result<RecruitDetailVO> detail(Integer recruitId) {
        log.info("招新详情:{}", recruitId);
        RecruitDetailVO  recruitDetailVO = recruitService.getRecruitDetail(recruitId);
        return Result.success(recruitDetailVO);
    }

    /**
     * 申请加入社团
     * @param applyClubDTO
     * @return
     */
    @PostMapping
    @ApiOperation(value = "申请加入社团")
    public Result<String> applyClub(@RequestBody ApplyClubDTO applyClubDTO){
        recruitService.applyClub(applyClubDTO);
        return Result.success();
    }


}
