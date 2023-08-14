package com.luas.controller.admin;


import com.luas.Properties.JwtProperties;
import com.luas.constant.JwtClaimsConstant;
import com.luas.constant.MessageConstant;
import com.luas.content.BaseContext;
import com.luas.dto.AdminLoginDto;
import com.luas.entity.Admin;
import com.luas.exception.CodeErrorException;
import com.luas.result.Result;
import com.luas.service.AdminService;
import com.luas.utils.JwtUtil;
import com.luas.vo.AdminInfoVo;
import com.luas.vo.AdminLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/user")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private JwtProperties jwtProperties;
    /**
     * 管理员登录
     * @param adminLoginDto
     * @return
     */
    @PostMapping("/login")
    public Result<AdminLoginVo> login(@RequestBody AdminLoginDto adminLoginDto){
        String uuid = adminLoginDto.getUuid();
        String code = adminLoginDto.getCode();
        String redisCode =(String) redisTemplate.opsForValue().get(uuid);
        if (!code.equals(redisCode)){
            throw new CodeErrorException(MessageConstant.CORD_ERROR);
        }
        Admin admin = adminService.login(adminLoginDto);
        //为微信用户生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.ADMIN_ID,admin.getId());  //其中，JwtClaimsConstant.USER_ID常量已定义。
        String token = JwtUtil.createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), claims);
        AdminLoginVo adminLoginVo = AdminLoginVo.builder()
                .token(token)
                .build();

        return Result.success(adminLoginVo);
    }

    /**
     * 查找用户信息
     * @return
     */
    @GetMapping
    public Result<AdminInfoVo> getAdminInfo(){
        Admin admin = adminService.getAdminInfo();

        AdminInfoVo adminInfoVo = AdminInfoVo.builder()
                .id(BaseContext.getCurrentId())
                .username(admin.getUsername())
                .floorname(admin.getFloorname())
                .phone(admin.getPhone())
                .type(admin.getType())
                .build();

        return Result.success(adminInfoVo);
    }
}
