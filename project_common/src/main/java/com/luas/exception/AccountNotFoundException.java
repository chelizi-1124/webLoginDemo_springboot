package com.luas.exception;

/**
 * 账号不存在
 */
public class AccountNotFoundException extends BaseException{
    public AccountNotFoundException(String message) {
        super(message);
    }
}
