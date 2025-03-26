package com.club.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class ManagerInfoVO {
    private Integer clubId;
    private Integer studentId;
    private Integer studentNum;
    private String studentName;
    private String clubName;
    private Integer sex;
    private String college;
    private String phoneNum;
    private String email;
    private String occupation;
}
