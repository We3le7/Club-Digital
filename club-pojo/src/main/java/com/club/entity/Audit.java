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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("audit")
@ApiModel(value="Audit对象", description="")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Audit implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "audit_id", type = IdType.AUTO)
    private Integer auditId;

    @TableField("audit_time")
    private LocalDateTime auditTime;

    @TableField("club_id")
    private Integer clubId;

    @TableField("is_pass")
    private Boolean isPass;

    @TableField("examiner_id")
    private Integer examinerId;

    @TableField("examiner_name")
    private String examinerName;

    @TableField("examine_feedback")
    private String examineFeedback;

}
