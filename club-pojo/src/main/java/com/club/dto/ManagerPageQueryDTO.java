package com.club.dto;

import lombok.Data;

@Data

public class ManagerPageQueryDTO {
    private String studentName;
    private String clubName;
    //页码
    private Integer page;

    //每页显示记录数
    private Integer pageSize;
}
