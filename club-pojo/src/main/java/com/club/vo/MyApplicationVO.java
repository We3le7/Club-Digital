package com.club.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class MyApplicationVO {
   private String clubName;
    private Integer clubID;
    private String district;
    private Integer applicantId;
    private String applicantName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime applyTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime examineTime;
    private String examinerName;
    private Integer state;
    private String affairName;
}
