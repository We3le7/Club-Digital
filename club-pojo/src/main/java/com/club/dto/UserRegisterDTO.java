package com.club.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class UserRegisterDTO {
    private Integer studentNum;
    private String studentName;
    private Integer sex;
    private String level;
    private String college;
    private Integer grade;
    private String phoneNum;
    private String email;
    private String studentPhotoUrl;
    private String password;
    private String code;
}
