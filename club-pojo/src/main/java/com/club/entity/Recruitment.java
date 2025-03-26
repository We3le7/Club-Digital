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
@TableName("recruitment")
@ApiModel(value="Recruitment对象", description="")
public class Recruitment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "recruit_id", type = IdType.AUTO)
    private Integer recruitId;

    @TableField("club_id")
    private Integer clubId;

    @TableField("start_time")
    private LocalDateTime startTime;

    @TableField("end_time")
    private LocalDateTime endTime;

    @TableField("introduction")
    private String introduction;

    @TableField("photo_url")
    private String photoUrl;
    @TableField("poster_url")
    private String posterUrl;


}
