package com.luas.result;


import lombok.Data;

import java.io.Serializable;

/**
 * 后端统一返回结果类
 */
@Data
public class Result<T> implements Serializable {

    private Integer code; //编码：1，成功 0和其他数字失败
    private String msg;//错误信息
    private T data;//数据

    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.code = 1; //使用了静态工厂方法
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.data = data;
        result.code = 1;
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 0;
        return result;
    }

}
