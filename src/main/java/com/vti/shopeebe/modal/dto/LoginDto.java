package com.vti.shopeebe.modal.dto;

import com.vti.shopeebe.modal.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    private int id;

    private String username;

    private Role role;

    private String fullname;

    private String userAgent; // Thông tin trình duyệt đang sử dụng

    private String token;
}
