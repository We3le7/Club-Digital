package com.club.service.impl;
import com.club.constant.RoleConstant;
import com.club.context.BaseContext;
import com.club.dto.*;
import com.club.entity.*;
import com.club.mapper.*;
import com.club.result.PageResult;
import com.club.service.RecruitService;
import com.club.vo.ApplyPageVO;
import com.club.vo.ClubInfoVO;
import com.club.vo.InterviewVO;
import com.club.vo.RecruitDetailVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.lang.System.currentTimeMillis;

@Slf4j
@Service
public class RecruitServiceImpl implements RecruitService {
    @Autowired
    private RecruitmentMapper recruitmentMapper;
    @Autowired
    private ClubInfoMapper clubInfoMapper;
    @Autowired
    private MembershipMapper membershipMapper;
    @Autowired
    private StudentInfoMapper studentInfoMapper;
    @Autowired
    private ApplicationJoinMapper applicationJoinMapper;

    public RecruitServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * 招新详情
     * @param recruitId
     * @return
     */
    public RecruitDetailVO getRecruitDetail(Integer recruitId){
        Recruitment  recruitment = recruitmentMapper.getRecruitmentById(recruitId);
        log.info("详情{}",recruitment);
        RecruitDetailVO recruitDetailVO = new RecruitDetailVO();
        BeanUtils.copyProperties(recruitment,recruitDetailVO);
        ClubInfo clubInfo = clubInfoMapper.getClubInfoById(recruitment.getClubId());
        recruitDetailVO.setClubName(clubInfo.getClubName());
        recruitDetailVO.setDistrict(clubInfo.getDistrict());
        recruitDetailVO.setClubType(clubInfo.getTypeId());
        Membership membership = membershipMapper.getMembershipByIdAndRole(recruitment.getClubId());
        StudentInfo studentInfo = studentInfoMapper.getById(membership.getStudentId());
        recruitDetailVO.setManagerName(studentInfo.getStudentName());
        return recruitDetailVO;
    }
    /**
     * 申请加入社团
     * @param applyClubDTO
     * @return
     */
    public void applyClub(ApplyClubDTO applyClubDTO){
        ApplicationJoin  applicationJoin = new ApplicationJoin();
        applicationJoin.setClubId(applyClubDTO.getClubId());
        applicationJoin.setRecruitId(applyClubDTO.getRecruitId());
        applicationJoin.setApplicantId(applyClubDTO.getApplicantId());
        applicationJoin.setApplicantName(applyClubDTO.getApplicantName());
        applicationJoin.setApplyTime(LocalDateTime.now());
        applicationJoinMapper.insert(applicationJoin);
    }
    /**
     * 招新分页查询
     */
    public PageResult pageQuery(RecruitPageQueryDTO recruitPageQueryDTO){
        PageHelper.startPage(recruitPageQueryDTO.getPage(),recruitPageQueryDTO.getPageSize());
        Page<ClubInfoVO> page = clubInfoMapper.pageQuery(recruitPageQueryDTO);
        return new PageResult(page.getTotal(),page.getResult());
    }
    /**
     * 发布招新
     * @param publishRecruitDTO
     * @return
     */
    public void publishRecruit(PublishRecruitDTO publishRecruitDTO){
        Recruitment recruitment = new Recruitment();
        BeanUtils.copyProperties(publishRecruitDTO,recruitment);
        recruitmentMapper.insert(recruitment);
    }

    /**
     * 批准加入
     * @param applyId
     */
    @Override
    public void approve(Integer applyId) {
        ApplicationJoin applicationJoin = applicationJoinMapper.getById(applyId);
        applicationJoin.setState(1);
        applicationJoin.setExamineTime(LocalDateTime.now());
        StudentInfo studentInfo = studentInfoMapper.getById(BaseContext.getCurrentId());
        applicationJoin.setExaminerId(studentInfo.getStudentId());
        applicationJoin.setExaminerName(studentInfo.getStudentName());
        applicationJoinMapper.update(applicationJoin);
        Membership membership = new Membership();
        membership.setClubId(applicationJoin.getClubId());
        membership.setStudentId(applicationJoin.getApplicantId());
        membership.setIsBelong(true);
        membership.setRole(RoleConstant.MEMBER);
        membershipMapper.insert(membership);
    }

    /**
     * 拒绝加入
     * @param applyId
     */

    @Override
    public void reject(Integer applyId) {
        ApplicationJoin applicationJoin = applicationJoinMapper.getById(applyId);
        applicationJoin.setState(0);
        applicationJoin.setExamineTime(LocalDateTime.now());
        StudentInfo studentInfo = studentInfoMapper.getById(BaseContext.getCurrentId());
        applicationJoin.setExaminerId(studentInfo.getStudentId());
        applicationJoin.setExaminerName(studentInfo.getStudentName());
        applicationJoinMapper.update(applicationJoin);

    }

    /**
     * 查看所有申请
     * @param applyPageDTO
     * @return
     */
    public PageResult getAllApplication(ApplyPageDTO applyPageDTO){
        PageHelper.startPage(applyPageDTO.getPage(),applyPageDTO.getPageSize());
        Page<ApplyPageVO> page = applicationJoinMapper.getPageApplication(applyPageDTO);
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 面试
     * @param email
     */
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String from;
    private  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd HH:mm");
    public void interview(InterviewDTO  interviewDTO) {
        List<InterviewVO> list = applicationJoinMapper.getByClubId(interviewDTO.getClubId());
        SimpleMailMessage message = new SimpleMailMessage();
        String subject = "社团面试通知";
        message.setFrom(from);

        for (InterviewVO interviewVO : list) {
            message.setTo(interviewVO.getEmail());
            message.setSubject(subject);

            String startTimeStr = interviewDTO.getStartTime();
            Date startTimeDate = null; // 现在 sdf 是类的成员变量，可以在这里使用
            try {
                startTimeDate = sdf.parse(startTimeStr);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            // 假设 interval 是以分钟为单位的整数
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startTimeDate);
            calendar.add(Calendar.MINUTE, interviewDTO.getInterval()); // 假设 getInterval() 返回 Integer

            // 将 Date 格式化为 String
            String updatedStartTimeStr = sdf.format(calendar.getTime());
            String content = "尊敬的" + interviewVO.getStudentName() +
                    "，时间为" + updatedStartTimeStr +
                    "，地点为" + interviewDTO.getAddress() + // 使用 interviewVO 而不是 interviewDTO
                    "，面试形式为" + interviewDTO.getFormat() + // 使用 interviewVO 而不是 interviewDTO
                    "，请准时参加面试。";

            message.setText(content);
            javaMailSender.send(message);
        }

    }

}
