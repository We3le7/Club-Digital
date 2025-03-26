package com.club.controller.user;

import com.club.constant.JwtClaimsConstant;
import com.club.constant.MessageConstant;
import com.club.dto.*;
import com.club.entity.StudentInfo;
import com.club.properties.JwtProperties;
import com.club.result.PageResult;
import com.club.result.Result;
import com.club.service.StudentService;
import com.club.utils.JwtUtil;
import com.club.utils.ValidateCodeUtils;
import com.club.vo.ApplyPageVO;
import com.club.vo.MyApplicationVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import com.club.vo.UserLoginVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/user/user")
@Slf4j
@Api(tags = "用户相关")
public class UserController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 登录
     *
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {

        UserLoginVO student = studentService.login(userLoginDTO);
        userLoginDTO.setPassword("******");
        log.info("用户登录：{}", userLoginDTO);
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, student.getStudentId());
        String token = JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims);
       student.setToken(token);
        return Result.success(student);
    }
    /**
     * 退出登录
     */
    @GetMapping("/logout")
    @ApiOperation(value = "退出登录")
    public Result<String> logout() {

        log.info("退出登录");
        return Result.success();
    }

    /**
     * 注册
     * @param userRegisterDTO
     * @return
     */
    @PostMapping("/register")
    @ApiOperation(value = "注册")
    public Result<String> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        log.info("用户注册：{}", userRegisterDTO);
        studentService.register(userRegisterDTO);
        return Result.success();
    }

    /**
     * 查询个人信息
     * @param studentId
     * @return
     */
    @GetMapping("/getInformation")
    @ApiOperation(value = "查询个人信息")
    public Result<UserRegisterDTO> getInformation(Integer studentId) {
        log.info("查询个人信息：Id:{}", studentId);
        UserRegisterDTO student = studentService.getById(studentId);
        return Result.success(student);
    }

    /**
     * 修改个人信息
     * @param studentId
     * @param userRegisterDTO
     * @return
     */
    @PutMapping("/updateInformation")
    @ApiOperation(value = "修改个人信息")
    public Result<String> updateInformation(Integer studentId, @RequestBody UserRegisterDTO userRegisterDTO) {
        log.info("修改个人信息：Id:{},studentInfo:{}", studentId, userRegisterDTO);
        studentService.updateById(studentId, userRegisterDTO);
        return Result.success();
    }
    /**
     * 获取验证码
     */
    @GetMapping("/getVerification")
    @ApiOperation(value = "获取验证码")
    public Result<String> getCode( String email) {

         String subject = "社团管理系统验证码";

        if (StringUtils.isNotEmpty(email)) {
            String code = ValidateCodeUtils.generateValidateCode(6).toString();
            String context = "欢迎使用社团管理系统，验证码为: " + code + ",五分钟内有效，请妥善保管!";
            log.info("code={}", code);
            // 真正地发送邮箱验证码
            studentService.getCode(email,subject,context);
            // 验证码由保存到session 优化为 缓存到Redis中，并且设置验证码的有效时间为 5分钟
            redisTemplate.opsForValue().set(email, code, 5, TimeUnit.MINUTES);
            return Result.success("验证码发送成功，请及时查看!");
        }
        return Result.error("验证码发送失败，请重新输入!");
    }

    /**
     * 找回密码
     */
    @PutMapping("/findPassword")
    @ApiOperation(value = "找回密码")
    public Result<String> findPassword(@RequestBody FindPasswordDTO findPasswordDTO) {
        studentService.findPassword(findPasswordDTO);
        return Result.success();
    }
    @GetMapping
    @ApiOperation(value = "查看已加入的所有社团")
    public Result<PageResult> page(MyClubDTO myClubDTO){
        log.info("查看已加入的所有社团：{}", myClubDTO);
        PageResult pageResult = studentService.pageQuery(myClubDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/application")
    @ApiOperation(value = "查看申请")
    public Result<List> application(Integer applicantId) {
        log.info("查看申请：{}", applicantId);
        List<MyApplicationVO> list= studentService.myApplication(applicantId);
        return Result.success(list);
    }




}
