package com.club.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("activity_picture")
@ApiModel(value="ActivityPicture对象", description="")
public class ActivityPicture implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "picture_id", type = IdType.AUTO)
    private Integer pictureId;

    @TableField("activity_id")
    private Integer activityId;

    @TableField("activity_picture_url")
    private String activityPictureUrl;


}
