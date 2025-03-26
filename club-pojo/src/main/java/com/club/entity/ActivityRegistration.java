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
@TableName("activity_registration")
@ApiModel(value="ActivityRegistration对象", description="")
public class ActivityRegistration implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "registration_id", type = IdType.AUTO)
    private Integer registrationId;

    @TableField("student_id")
    private Integer studentId;

    @TableField("activity_id")
    private Integer activityId;

    @TableField("registration_time")
    private LocalDateTime registrationTime;

    @TableField("is_attending")
    private Integer isAttending;


}
