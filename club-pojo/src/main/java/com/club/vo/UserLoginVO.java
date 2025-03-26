package com.club.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户登录返回的数据格式")
public class UserLoginVO {
    @ApiModelProperty("主键值")
    private Integer studentId;

    @ApiModelProperty("姓名")
    private String studentName;

    @ApiModelProperty("jwt令牌")
    private String token;
    private Integer clubId;
    private Integer role;




































}
