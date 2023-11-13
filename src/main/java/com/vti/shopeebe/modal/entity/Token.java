package com.vti.shopeebe.modal.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "token")
    private String token;

    @Column(name = "user_agent")
    private String userAgent; // Thông tin trình duyệt đăng nhập

    @Column(name = "expiration")
    private Date expiration;
}
