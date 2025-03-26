package com.club.dto;

import lombok.Data;

@Data
public class ApplyPageDTO {
    //姓名
    private String studentName;
    private Integer studentNum;
    private Integer state;
    private Integer clubId;
    //页码
    private Integer page;
    //每页显示记录数
    private Integer pageSize;
}
