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
import lombok.Getter;
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
@TableName("recommend")
@ApiModel(value="Recommend对象", description="")
@Getter
public class Recommend implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "recommend_id", type = IdType.AUTO)
    private Integer recommendId;

    @TableField("recommend_time")
    private LocalDateTime recommendTime;

    @TableField("club_id")
    private Integer clubId;

    @TableField("is_excellent")
    private Integer isExcellent;

    @TableField("examine_feedback")
    private String examineFeedback;

    @TableField("examiner_id")
    private Integer examinerId;

    @TableField("examiner_name")
    private String examinerName;
    @TableField("recommend_title")
    private String recommendTitle;


}
