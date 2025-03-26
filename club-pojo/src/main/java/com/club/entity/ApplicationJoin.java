package com.club.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
@TableName("application_join")
@ApiModel(value="ApplicationJoin对象", description="")
public class ApplicationJoin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "apply_id", type = IdType.AUTO)
    private Integer applyId;

    @TableField("apply_time")
    private LocalDateTime applyTime;

    @TableField("examine_time")
    private LocalDateTime examineTime;

    @TableField("applicant_id")
    private Integer applicantId;

    @TableField("examiner_id")
    private Integer examinerId;

    @TableField("applicant_name")
    private String applicantName;

    @TableField("examiner_name")
    private String examinerName;

    @TableField("state")
    private Integer state;

    @TableField("club_id")
    private Integer clubId;
    @TableField("recruit_id")
    private Integer recruitId;


}
