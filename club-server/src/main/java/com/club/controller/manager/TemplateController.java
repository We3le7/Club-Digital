package com.club.controller.manager;

import com.club.dto.ActivityAddManagerDTO;
import com.club.result.PageResult;
import com.club.result.Result;
import com.club.service.ActivityOverviewManagerService;
import com.club.service.TemplateService;
import com.club.utils.AliOssUtil;
import com.club.vo.ActivityDetailsManagerVO;
import com.club.vo.StudentInfoVO;
import com.club.vo.TemplateDownloadVO;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Api(tags = "模板相关接口")
@RestController
@RequestMapping("/user/manager/template")
@Slf4j
public class TemplateController {
    @Autowired private TemplateService templateService;
    @GetMapping("")
    public Result<List<TemplateDownloadVO>> getTemplate(Integer type) {
        List<TemplateDownloadVO> list = templateService.getTemplate(type);
        return Result.success(list);
    }

}
