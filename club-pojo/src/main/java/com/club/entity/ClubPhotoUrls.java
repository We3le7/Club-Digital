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
@TableName("club_photo_urls")
@ApiModel(value="ClubPhotoUrls对象", description="")
public class ClubPhotoUrls implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "club_id", type = IdType.AUTO)
    private Integer clubId;

    @TableField("club_photo_url")
    private String clubPhotoUrl;


}
