package com.luas.service.impl;

import com.luas.constant.MessageConstant;
import com.luas.content.BaseContext;
import com.luas.dto.AdminLoginDto;
import com.luas.entity.Admin;
import com.luas.exception.AccountNotFoundException;
import com.luas.exception.PasswordErrorException;
import com.luas.mapper.AdminMapper;
import com.luas.result.Result;
import com.luas.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;



    /**
     * 管理员登录
     * @param adminLoginDto
     * @return
     */
    @Override
    public Admin login(AdminLoginDto adminLoginDto) {
        String userId = adminLoginDto.getUserId();
        String password = adminLoginDto.getPassword();

        //根据账号查询用户
        Admin admin = adminMapper.login(userId);
        //处理各种异常
        if (admin == null) {
            throw  new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        //密码比对
        //对前端传入的密码进行MD5加密再比对
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(admin.getPassword())){
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        //返回对象实体
        return admin;

    }

    /**
     * 查找用户信息
     * @return
     */
    @Override
    public Admin getAdminInfo() {
        Long id = BaseContext.getCurrentId();
        return adminMapper.getAdminInfo(id);
    }
}
