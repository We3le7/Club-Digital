package com.club.dto;

import lombok.Data;

@Data
public class ClubInfoUserDTO {
    //表示当前的页码
    private Integer pageNum;

    //表示当前每一页的大小
    private Integer pageSize;

    //社团名称
    private String clubName;

    //社团类型
    private String clubType;

    //所在校区
    private String district;
}
