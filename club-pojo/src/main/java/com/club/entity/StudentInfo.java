package com.club.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2024-05-15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("student_info")
@ApiModel(value="StudentInfo对象", description="")
public class StudentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "student_id", type = IdType.AUTO)
    private Integer studentId;

    @TableField("student_num")
    private Integer studentNum;

    @TableField("student_name")
    private String studentName;

    @TableField("sex")
    private Integer sex;

    @TableField("level")
    private Integer level;

    @TableField("college")
    private String college;

    @TableField("grade")
    private Integer grade;

    @TableField("phone_num")
    private String phoneNum;

    @TableField("email")
    private String email;

    @TableField("student_photo_url")
    private String studentPhotoUrl;

    @TableField("password_hash")
    private String passwordHash;


}
