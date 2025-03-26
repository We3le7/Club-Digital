package com.club.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecruitPageQueryDTO {

    //员工姓名
    private String clubName;

    //社团类型
    private String clubType;

    //校区
    private String district;

    //页码
    private Integer page;

    //每页显示记录数
    private Integer pageSize;

}
