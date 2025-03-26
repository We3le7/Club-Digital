package com.club.dto;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class ActivityOverviewAdminDTO {
    //表示当前的页码
    private Integer pageNum;

    //表示当前每一页的大小
    private Integer pageSize;

    //机关部处Id
    private Integer departmentId;

    //活动标题
    private String title;

    //社团Id
    private Integer clubId;

    //校区Id——新增
    private Integer district;

    //审核状态
    private Integer state;

    //是否按申请时间排序
    private Integer byApplicationDatetime;
}
