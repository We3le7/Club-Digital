package com.club.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ClubInfoStaticVO {

    private Integer clubId;
    private String clubName;
    private Integer undergrad;
    private Integer grad;
    private Integer male;
    private Integer female;
    private Integer typeName;
    private Integer campusName;
}
