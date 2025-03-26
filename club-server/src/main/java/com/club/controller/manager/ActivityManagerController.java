package com.club.controller.manager;

import com.club.utils.AliOssUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.club.dto.ActivityAddManagerDTO;
import com.club.dto.ActivityOverviewManagerDTO;
import com.club.result.PageResult;
import com.club.result.Result;
import com.club.service.ActivityOverviewManagerService;
import com.club.vo.ActivityDetailsManagerVO;
import com.club.vo.ClubInfoUserVO;
import com.club.vo.StudentInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Field;
import javax.xml.crypto.Data;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Api(tags = "活动相关接口")
@RestController
@RequestMapping("/user/manager/activity")
@Slf4j
public class ActivityManagerController {
    @Autowired
    private ActivityOverviewManagerService  activityOverviewManagerService;

    @Autowired
    private AliOssUtil aliOssUtil;
    /***
     * 社团管理端按照条件获取活动概览
     * @param pageNum,pageSize,userId,clubId,title,district,state,byViews,byFavorites,byReviewDatetime,reviewDateStart,reviewDateEnd
     * @return
     */
    @GetMapping("/get")
    @ApiOperation("社团管理端按照条件获取活动概览")
    public Result<PageResult> getActivityOverviewByConditions(Integer pageNum,Integer pageSize,Integer userId,
                                                              Integer clubId,String title,Integer district,
                                                              Integer state,Integer byViews,Integer byFavorites,
                                                              Integer byReviewDatetime,String reviewDateStart,String reviewDateEnd){
        log.info("社团管理端按照条件获取活动概览:{}",pageNum,pageSize,userId,clubId,title,district,state,byViews,byFavorites,byReviewDatetime,reviewDateStart,reviewDateEnd);
        PageResult pageResult =  activityOverviewManagerService.getActivityOverviewByConditions(pageNum,pageSize,userId,clubId,title,district,state,byViews,byFavorites,byReviewDatetime,reviewDateStart,reviewDateEnd);
        return Result.success(pageResult);
    }

    /***
     * 社团管理端获取该用户所管的社团信息
     * @param userId
     * @return
     */
    @GetMapping("/get_club")
    @ApiOperation("社团管理端获取该用户所管的社团信息")
    public Result<PageResult> getClubInfoByUserId( Integer userId, Integer pageNum, Integer pageSize){
        log.info("社团管理端获取该用户所管的社团信息:{}",userId);
        PageResult pageResult =  activityOverviewManagerService.getClubInfoByUserId(userId,pageNum,pageSize);
        return  Result.success(pageResult);
    }

    /***
     * 社团管理端获取报名列表
     * @param activityId
     * @return
     */
    @GetMapping("get_registration_list")
    @ApiOperation("社团管理端获取报名列表")
    public Result<String> getStudentInfoByActivityId(Integer activityId){
        StudentInfoVO studentInfoVO = new StudentInfoVO();
        try {
            // 查询数据库找出报名的学生数据
            List<StudentInfoVO> dataList = activityOverviewManagerService.getStudentInfoByActivityId(activityId);

            // 创建PDF
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();

            Class<?> clazz = studentInfoVO.getClass();
            Field[] fields = clazz.getDeclaredFields();

            Map<String, String> fieldMap = new HashMap<>();
            fieldMap.put("studentId", "学生ID");
            fieldMap.put("studentNum", "学号");
            fieldMap.put("studentName", "学生姓名");
            fieldMap.put("sex", "性别");
            fieldMap.put("level", "级别");
            fieldMap.put("college", "学院");
            fieldMap.put("grade", "年级");
            fieldMap.put("phoneNum", "电话号码");
            fieldMap.put("email", "邮箱");

            //创建表格
            PdfPTable table = new PdfPTable(fields.length);
            // 创建字体
            BaseFont bfChinese = BaseFont.createFont("C:/Windows/Fonts/simfang.ttf",  BaseFont.IDENTITY_H, 	BaseFont.NOT_EMBEDDED);
            Font fontChinese = new Font(bfChinese, 10, Font.NORMAL);

            //添加表头
            for (Field field : fields) {
                field.setAccessible(true); // 设置字段可访问

                try {
                    Object value = field.getName(); // 获取字段名作为表头标题
                    String chineseName = fieldMap.get(value.toString());
                    PdfPCell cell = new PdfPCell(new Phrase(chineseName, fontChinese)); // 使用中文字体
                    table.addCell(cell);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // 将每条数据加入PDF
            for (StudentInfoVO date : dataList) {
                table.addCell(new PdfPCell(new Phrase(date.getStudentId().toString(), fontChinese)));
                table.addCell(new PdfPCell(new Phrase(date.getStudentNum().toString(),fontChinese)));
                table.addCell(new PdfPCell(new Phrase(date.getStudentName().toString(),fontChinese)));
                table.addCell(new PdfPCell(new Phrase(date.getSex().toString(),fontChinese)));
                table.addCell(new PdfPCell(new Phrase(date.getLevel().toString(),fontChinese)));
                table.addCell(new PdfPCell(new Phrase(date.getCollege().toString(),fontChinese)));
                table.addCell(new PdfPCell(new Phrase(date.getGrade().toString(),fontChinese)));
                table.addCell(new PdfPCell(new Phrase(date.getPhoneNum().toString(),fontChinese)));
                table.addCell(new PdfPCell(new Phrase(date.getEmail().toString(),fontChinese)));
            }

            //将表格添加到文件中
            document.add(table);
            document.close();

            //将该文件上传到阿里云服务器
            byte[] pdfData = outputStream.toByteArray();
            String objectName= UUID.randomUUID().toString()+".pdf";
            String filePath = aliOssUtil.upload(pdfData, objectName);

            return  Result.success(filePath);
        } catch (Exception e) {
            e.printStackTrace();
            // 错误处理
            return Result.error("error");
        }
    }

    /***
     * 社团管理端获取源活动详情
     * @param userId
     * @param clubId
     * @param activityId
     * @return
     */
    @GetMapping("get_activity")
    @ApiOperation("社团管理端获取源活动详情")
    public Result<ActivityDetailsManagerVO> getActivityDetailsByActivityId( Integer userId, Integer clubId, Integer activityId){
        log.info("社团管理端获取源活动详情:{}",activityId);
        ActivityDetailsManagerVO activityDetailsManagerVO =  activityOverviewManagerService.getActivityDetailsByActivityId(userId,clubId,activityId);
        return Result.success(activityDetailsManagerVO);
    }

    /***
     * 社团管理端删除活动
     * @param activityId
     * @return
     */
    @DeleteMapping("delete_activity")
    @ApiOperation("社团管理端删除活动")
    public Result<String> deleteActivityByActivityId(Integer activityId){
        log.info("社团管理端删除活动:{}",activityId);
        activityOverviewManagerService.deleteActivityByActivityId(activityId);
        return Result.success("删除成功");
    }

    /***
     * 社团管理端添加新的活动
     * @param activityAddManagerDTO
     * @return
     */
    @PostMapping("add_activity")
    @ApiOperation("社团管理端添加新的活动")
    public Result<String> addActivity(ActivityAddManagerDTO activityAddManagerDTO){
        log.info("社团管理端添加新的活动:{}",activityAddManagerDTO);
        //添加当天的发布时间
        LocalDateTime currentDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        activityAddManagerDTO.setApplicationDatetime(formattedDateTime);

        activityOverviewManagerService.addActivity(activityAddManagerDTO);
        return Result.success("添加成功");
    }

    /***
     * 社团管理端修改活动
     * @param activityAddManagerDTO
     * @return
     */
    @PutMapping("update_activity")
    @ApiOperation("社团管理端修改活动")
    public Result<String> updateActivity(ActivityAddManagerDTO activityAddManagerDTO){
        log.info("社团管理端修改活动:{}",activityAddManagerDTO);
        activityOverviewManagerService.updateActivity(activityAddManagerDTO);
        return Result.success("修改成功");
    }
}
