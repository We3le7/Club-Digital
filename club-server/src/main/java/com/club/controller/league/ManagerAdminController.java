package com.club.controller.league;

import com.club.dto.ManagerPageQueryDTO;
import com.club.dto.RoleDTO;
import com.club.result.PageResult;
import com.club.result.Result;
import com.club.service.ManagerManagementService;
import com.club.vo.ManagerInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/admin/league/managerManagement")
@Api(tags = "社管管理")
public class ManagerAdminController {
    @Autowired
    private ManagerManagementService managerManagementService;
    /**
     * 社管查询
     * @param managerPageQueryDTO
     * @return
     */
    @GetMapping
    @ApiOperation(value= "分页查询社管信息")
    public Result<PageResult> getManagerInfo(ManagerPageQueryDTO managerPageQueryDTO){
        log.info("社管分页查询：{}", managerPageQueryDTO);
        PageResult pageResult = managerManagementService.pageQuery(managerPageQueryDTO);
        return Result.success(pageResult);

    }
    @PutMapping(value = "/updateManager")
    @ApiOperation(value= "修改角色属性")
    public Result<String> updateManagerRole(@RequestBody RoleDTO roleDTO){
        log.info("修改角色属性：{}", roleDTO);
        managerManagementService.updateManagerRole(roleDTO);
        return Result.success();
    }
}
