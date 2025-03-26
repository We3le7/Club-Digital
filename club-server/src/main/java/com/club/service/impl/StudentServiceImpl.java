package com.club.service.impl;

import com.club.constant.LevelConstant;
import com.club.constant.MessageConstant;
import com.club.constant.RoleConstant;
import com.club.dto.*;
import com.club.entity.StudentInfo;
import com.club.exception.AccountNotFoundException;
import com.club.exception.PasswordErrorException;
import com.club.mapper.ApplicationJoinMapper;
import com.club.mapper.ApplicationQuitMapper;
import com.club.mapper.ClubInfoMapper;
import com.club.mapper.StudentInfoMapper;
import com.club.result.PageResult;
import com.club.service.StudentService;
import com.club.vo.ApplyPageVO;
import com.club.vo.MyApplicationVO;
import com.club.vo.MyClubPageVO;
import com.club.vo.UserLoginVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentInfoMapper studentInfoMapper;
    @Autowired
    private ClubInfoMapper clubInfoMapper;
    @Autowired
    private ApplicationJoinMapper applicationJoinMapper;
    @Autowired
    private ApplicationQuitMapper applicationQuitMapper;

    public StudentServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * 登录
     * @param userLoginDTO
     * @return
     */
    public UserLoginVO login(UserLoginDTO userLoginDTO){
        String email = userLoginDTO.getEmail();
        String password = userLoginDTO.getPassword();

        //1、根据邮箱查询数据库中的数据
        StudentInfo studentInfo = studentInfoMapper.getByEmail(email);

        //2、判断数据库中的数据是否为空
        if(studentInfo == null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //3、判断密码是否正确
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if(!password.equals(studentInfo.getPasswordHash())){
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        UserLoginVO userLoginVO = studentInfoMapper.getLoginById(studentInfo.getStudentId());
        if (Objects.equals(userLoginDTO.getIdentity(), "社管端")){
            Integer role = userLoginVO.getRole();
            if (role == null) {
                throw new AccountNotFoundException("无权限登录社管端");
            } else if(role==RoleConstant.MEMBER){
                throw new AccountNotFoundException("无权限登录社管端");
            }

        }
        //4、返回用户信息
        return userLoginVO;

    }
    /**
     * 查询个人信息
     * @param studentId
     * @return
     */
    public UserRegisterDTO getById(Integer studentId){
        StudentInfo studentInfo = studentInfoMapper.getById(studentId);
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        studentInfo.setPasswordHash("******");
        BeanUtils.copyProperties(studentInfo,userRegisterDTO);
        if(studentInfo.getLevel().equals(LevelConstant.BENKE)){
            userRegisterDTO.setLevel("本科生");
        }else if(studentInfo.getLevel().equals(LevelConstant.SHUOSHI)){
            userRegisterDTO.setLevel("研究生");
        }
        return userRegisterDTO;
    }
    /**
     * 注册
     * @param userRegisterDTO
     * @return
     */
    public void register(UserRegisterDTO userRegisterDTO){
        Integer result = checkCode(userRegisterDTO.getEmail(),userRegisterDTO.getCode());
        if(result == 1){
            StudentInfo studentInfo = new StudentInfo();
            BeanUtils.copyProperties(userRegisterDTO,studentInfo);
            if(userRegisterDTO.getLevel().equals("本科生")){
                log.info("本科生");
                studentInfo.setLevel(LevelConstant.BENKE);
            }else if(userRegisterDTO.getLevel().equals("研究生")||userRegisterDTO.getLevel().equals("硕士")){
                studentInfo.setLevel(LevelConstant.SHUOSHI);
            }
            studentInfo.setPasswordHash(DigestUtils.md5DigestAsHex(userRegisterDTO.getPassword().getBytes()));
            studentInfoMapper.insert(studentInfo);
        }else if(result == 0){
            throw new PasswordErrorException(MessageConstant.CODE_ERROR);
        }else if(result == -1){
            throw new PasswordErrorException(MessageConstant.CODE_NOT_FOUND);
        }
    }

    /**
     * 修改个人信息
     * @param studentId
     * @param userRegisterDTO
     * @return
     */
   public void updateById(Integer studentId, UserRegisterDTO userRegisterDTO){
        StudentInfo studentInfo = new StudentInfo();
        BeanUtils.copyProperties(userRegisterDTO,studentInfo);
        studentInfo.setStudentId(studentId);
        if(userRegisterDTO.getLevel().equals("本科生")){
            log.info("本科生");
            studentInfo.setLevel(LevelConstant.BENKE);
        }else if(userRegisterDTO.getLevel().equals("研究生")||userRegisterDTO.getLevel().equals("硕士")){
            studentInfo.setLevel(LevelConstant.SHUOSHI);
        }
        studentInfo.setPasswordHash(DigestUtils.md5DigestAsHex(userRegisterDTO.getPassword().getBytes()));
        studentInfoMapper.updateById(studentInfo);
   }

    /**
     * 发送验证码
     * @param email
     */
    private final JavaMailSender  javaMailSender;
    @Value("${spring.mail.username}")
    private String from;
    @Override
    public void getCode(String email,String subject,String context){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject(subject);
        message.setText(context);
        javaMailSender.send(message);
       
    }

    /**
     * 验证码
     * @param email
     * @param code
     * @return
     */
    @Autowired
    private RedisTemplate redisTemplate;

    public Integer checkCode(String email,String code){
        Integer result=1;
        ValueOperations valueOperations =  redisTemplate.opsForValue();
        String msgKey = email;
        Object value = valueOperations.get(msgKey);
        log.info("验证码："+value);
        log.info("用户输入的验证码："+code);
        if(value == null){
            result = -1;
        }else if(!code.equals(value)) {
            result = 0;
        }else if(code.equals(value)){
            result = 1;
            redisTemplate.delete(msgKey);
        }
        return result;
    }

    public void findPassword(FindPasswordDTO findPasswordDTO){
        Integer result = checkCode(findPasswordDTO.getEmail(),findPasswordDTO.getCode());
        if(result == 1){
            StudentInfo studentInfo = new StudentInfo();
            studentInfo.setEmail(findPasswordDTO.getEmail());
            studentInfo.setPasswordHash(DigestUtils.md5DigestAsHex(findPasswordDTO.getPassword().getBytes()));
            studentInfoMapper.updateByEmail(studentInfo);
        }else if(result == 0){
            throw new PasswordErrorException(MessageConstant.CODE_ERROR);
        }else if(result == -1){
            throw new PasswordErrorException(MessageConstant.CODE_NOT_FOUND);
        }
    }
    public  PageResult pageQuery(MyClubDTO myClubDTO){
        PageHelper.startPage(myClubDTO.getPage(),myClubDTO.getPageSize());
        Page<MyClubPageVO> page = clubInfoMapper.pageMyClubQuery(myClubDTO);
        return new PageResult(page.getTotal(),page.getResult());
    }
    public List<MyApplicationVO> myApplication(Integer applicantId){
        List<MyApplicationVO>  list1 = applicationJoinMapper.myApplication(applicantId);
        for (MyApplicationVO vo : list1) {
            vo.setAffairName("申请加入社团");
        }

        List<MyApplicationVO>  list2 = applicationQuitMapper.myApplication(applicantId);
        for (MyApplicationVO vo : list2) {
            vo.setAffairName("申请退出社团");
        }
        list1.addAll(list2);
        log.info("list1{}",list1);
        return list1;
    }

}
