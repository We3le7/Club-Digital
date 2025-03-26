package com.club.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllRecommendResultVO {
    private Integer clubId;
    private String clubName;
    private Integer isExcellent;
}
