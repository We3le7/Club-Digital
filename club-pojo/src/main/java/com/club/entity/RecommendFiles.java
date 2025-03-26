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
@TableName("recommend_files")
@ApiModel(value="RecommendFiles对象", description="")
public class RecommendFiles implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "recommend_id", type = IdType.AUTO)
    private Integer recommendId;

    @TableField("recommend_file")
    private String recommendFile;


}
