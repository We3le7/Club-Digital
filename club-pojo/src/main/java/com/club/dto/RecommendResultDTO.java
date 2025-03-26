package com.club.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class RecommendResultDTO {

    private Integer clubId;
    private Integer isExcellent;
    private Integer examinerId;
    private String examinerName;
    private String examineFeedback;
//    private String recommendTitle;
}
