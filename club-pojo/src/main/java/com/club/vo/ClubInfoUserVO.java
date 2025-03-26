package com.club.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ClubInfoUserVO {
    //社团Id
    private Integer clubId;
    
    //社团名称
    private String clubName;
    
    //社团介绍
    private String clubIntro;
    
    //社团成员数量
    private Long numOfMember;

    //社团类型
    private String clubType;

    //所在校区
    private String district;

    //社团会长
    private String clubLeader;

    //社团照片
    private List<String> clubPhotoUrls=new ArrayList<>();

//    private Integer clubType;
//    private Integer district;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime startTime;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime endTime;
}
