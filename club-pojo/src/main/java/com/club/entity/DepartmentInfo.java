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
@TableName("department_info")
@ApiModel(value="DepartmentInfo对象", description="")
public class DepartmentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "department_id", type = IdType.AUTO)
    private Integer departmentId;

    @TableField("department_account")
    private String departmentAccount;

    @TableField("department_name")
    private String departmentName;

    @TableField("password")
    private String password;

    @TableField("district")
    private Integer district;

    @TableField("department_type_id")
    private Integer departmentTypeId;


}
