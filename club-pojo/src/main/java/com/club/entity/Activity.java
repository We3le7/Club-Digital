package com.club.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("activity")
@ApiModel(value="Activity对象", description="")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "activity_id", type = IdType.AUTO)
    private Integer activityId;

    @TableField("title")
    private String title;

    @TableField("district")
    private Integer district;

    @TableField("specific_addr")
    private String specificAddr;

    @TableField("views")
    private Integer views;

    @TableField("favorites")
    private Integer favorites;

    @TableField("state")
    private Integer state;

    @TableField("comment")
    private String comment;

    @TableField("start_datetime")
    private LocalDateTime startDatetime;

    @TableField("end_datetime")
    private LocalDateTime endDatetime;

    @TableField("require_registration")
    private Integer requireRegistration;

    @TableField("registration_start")
    private LocalDateTime registrationStart;

    @TableField("registration_end")
    private LocalDateTime registrationEnd;

    @TableField("application_datetime")
    private LocalDateTime applicationDatetime;

    @TableField("review_datetime")
    private LocalDateTime reviewDatetime;

    @TableField("visibility")
    private Integer visibility;

    @TableField("responsible_person")
    private String responsiblePerson;

    @TableField("phonenum")
    private String phonenum;

    @TableField("content")
    private String content;

    @TableField("club_id")
    private Integer clubId;

    @TableField("department_id")
    private Integer departmentId;

    @TableField("activity_type_id")
    private Integer activityTypeId;


}
