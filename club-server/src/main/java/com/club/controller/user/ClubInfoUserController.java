package com.club.controller.user;

import com.club.dto.ClubInfoUserDTO;
import com.club.result.PageResult;
import com.club.result.Result;
import com.club.service.ClubUserService;
import com.club.vo.ClubInfoUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "社团相关接口")
@RestController
@RequestMapping("/user/club")
@Slf4j
public class ClubInfoUserController {
    @Autowired
    private ClubUserService clubUserService;

    /***
     * 社团分页查询
     * @param clubInfoUserDTO
     * @return
     */
    @GetMapping("/show")
    @ApiOperation("分页查询社团")
    public Result<PageResult> page(ClubInfoUserDTO clubInfoUserDTO){
        log.info("分页查询社团:{}",clubInfoUserDTO);
        PageResult pageResult = clubUserService.pageQuery(clubInfoUserDTO);
        return Result.success(pageResult);
    }


    /***
     * 根据id查询社团的详细信息
     * @param clubId
     * @return
     */
    @GetMapping
    @ApiOperation("根据id查询社团")
    public Result<ClubInfoUserVO> ClubInfoGetById(Integer clubId){
        log.info("根据id查询社团:{}",clubId);
        ClubInfoUserVO  clubInfoUserVO = clubUserService.clubInfoGetById(clubId);
        return Result.success(clubInfoUserVO);
    }

}
