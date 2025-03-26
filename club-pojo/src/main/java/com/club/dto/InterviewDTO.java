package com.club.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalTime;

@Data

public class InterviewDTO {
   private Integer clubId;
   private String address;
   private String format;
   private String startTime;
   private Integer interval;
   private Integer perNum;
}
