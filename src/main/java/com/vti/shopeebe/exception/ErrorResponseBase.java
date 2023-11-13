package com.vti.shopeebe.exception;

import org.springframework.http.HttpStatus;

public enum ErrorResponseBase {
    NOT_FOUND(HttpStatus.NOT_FOUND, "Đối tượng không tồn tại"),
    USERNAME_EXISTED(HttpStatus.INTERNAL_SERVER_ERROR, "Username đã tồn tại"),
    MIN_MAXINVALID(HttpStatus.BAD_REQUEST, "Số min phải nhỏ hơn max"),
    LOGIN_FAILS(HttpStatus.UNAUTHORIZED, "Người dùng không tồn tại"),
    LOGIN_FAILS_USERNAME(HttpStatus.UNAUTHORIZED, "Người dùng không tồn tại"),
    LOGIN_FAILS_PASSWORD(HttpStatus.UNAUTHORIZED, "Mật khẩu không đúng")
    ;


    public final HttpStatus status;
    public final String message;

    ErrorResponseBase(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus(){
        return status;
    }

    public String getMessage(){
        return message;
    }
}
