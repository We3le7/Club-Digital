package com.club.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ApplyPageVO {
    private Integer applyId;
    private Integer studentId;
    private Integer studentNum;

    private String studentName;

    private Integer sex;

    private Integer level;

    private String college;

    private Integer grade;

    private String phoneNum;

    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime applyTime;
    private Integer state;
}
