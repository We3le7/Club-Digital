package com.club.dto;

import io.swagger.models.auth.In;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ClubInfoManagerDTO {
    //社团Id
    private Integer clubId;

    //社团名称
    private String clubName;

    //社团介绍
    private String clubIntro;

    //社团成员数量
    private Long numOfMember;

    //社团类型——修改
    private String clubType;

    //社团类型Id
    private Integer clubTypeId;

    //所在校区
    private String district;

    //所在校区的Id
    private Integer districtId;

    //社团会长的学生ID
    private String clubLeader;

    //社团照片
    private List<String> clubPhotoUrls=new ArrayList<>();
}
