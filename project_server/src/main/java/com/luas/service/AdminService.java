package com.luas.service;

import com.luas.dto.AdminLoginDto;
import com.luas.entity.Admin;

public interface AdminService {

    /**
     * 管理员登录
     * @param adminLoginDto
     * @return
     */
    Admin login(AdminLoginDto adminLoginDto);

    /**
     * 查找用户信息
     * @return
     */
    Admin getAdminInfo();
}
