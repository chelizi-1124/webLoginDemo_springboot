package com.luas.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class AdminLoginDto implements Serializable {
    private String userId;
    private String password;
    private String type;
    private String code;
    private String uuid;
}
