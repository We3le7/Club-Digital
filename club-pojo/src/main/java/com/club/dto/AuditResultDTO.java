package com.club.dto;

import lombok.Data;

@Data
public class AuditResultDTO {

    private Integer clubId;
    private Boolean isPass;
    private Integer examinerId;

    private String examinerName;
    private String examineFeedback;
}
