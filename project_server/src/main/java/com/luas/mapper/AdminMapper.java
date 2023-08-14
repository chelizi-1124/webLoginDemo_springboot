package com.luas.mapper;

import com.luas.dto.AdminLoginDto;
import com.luas.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {

    /**
     * 登录
     */

    @Select("select * from admin where user_id = #{userId}")
    Admin login(String userId);

    /**
     * 查找用户信息
     * @param id
     * @return
     */
    @Select("select * from admin where id = #{id}")
    Admin getAdminInfo(Long id);
}
