package com.club.controller.admin;

import com.club.constant.JwtClaimsConstant;
import com.club.dto.AdminUserLoginDTO;
import com.club.entity.DepartmentInfo;
import com.club.properties.JwtProperties;
import com.club.result.Result;
import com.club.service.AdminUserService;
import com.club.utils.JwtUtil;
import com.club.vo.AdminUserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/admin")
@Api(tags = "机关部处用户相关")
public class AdminUserController {
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private AdminUserService adminUserService;
    /**
     * 登录
     *
     * @param adminUserLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "机关部处登录")
    public Result<AdminUserLoginVO> login(@RequestBody AdminUserLoginDTO adminUserLoginDTO) {

        DepartmentInfo departmentInfo = adminUserService.login(adminUserLoginDTO);
        adminUserLoginDTO.setPassword("******");
        log.info("用户登录：{}", adminUserLoginDTO);
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, departmentInfo.getDepartmentId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);
        AdminUserLoginVO adminUserLoginVO = AdminUserLoginVO.builder()
                .departmentId(departmentInfo.getDepartmentId())
                .departmentName(departmentInfo.getDepartmentName())
                .token(token)
                .build();
        return Result.success(adminUserLoginVO);
    }
    @GetMapping("/getInformation")
    @ApiOperation(value = "获取部门信息")
    public Result<DepartmentInfo> getInformation(Integer departmentId) {
        DepartmentInfo departmentInfo = adminUserService.getInformation(departmentId);
        log.info("获取部门信息：{}", departmentInfo);
        departmentInfo.setPassword("******");
        return Result.success(departmentInfo);
    }
}
