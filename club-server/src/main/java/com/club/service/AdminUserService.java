package com.club.service;

import com.club.dto.AdminUserLoginDTO;
import com.club.entity.DepartmentInfo;

public interface AdminUserService {
    DepartmentInfo login(AdminUserLoginDTO adminUserLoginDTO);

    DepartmentInfo getInformation(Integer departmentId);
}
