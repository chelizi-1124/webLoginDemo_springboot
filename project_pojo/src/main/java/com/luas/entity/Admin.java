package com.luas.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    //职工号
    private String userId;
    //身份
    private String type;
    //姓名
    private String username;
    //密码
    private String password;
    //手机号
    private String phone;
    //公寓楼名
    private String floorname;
    //创建时间
    private LocalDateTime createTime;

}
