package com.club.dto;

import lombok.Data;

@Data

public class FindPasswordDTO {
    private String email;
    private String code;
    private String password;
}
