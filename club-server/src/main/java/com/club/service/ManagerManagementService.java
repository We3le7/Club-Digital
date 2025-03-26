package com.club.service;

import com.club.dto.ManagerPageQueryDTO;
import com.club.dto.RoleDTO;
import com.club.result.PageResult;

public interface ManagerManagementService {
    PageResult pageQuery(ManagerPageQueryDTO managerPageQueryDTO);

    void updateManagerRole(RoleDTO roLeDTO);
}
