package com.club.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentInfoVO implements Serializable {

    private Integer studentId;

    private Integer studentNum;

    private String studentName;

    private Integer sex;

    private Integer level;

    private String college;

    private Integer grade;

    private String phoneNum;

    private String email;


}