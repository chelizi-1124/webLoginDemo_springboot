package com.luas.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminInfoVo implements Serializable {
    private Long id;
    private String username;
    private String floorname;
    private String phone;
    private String type;
}