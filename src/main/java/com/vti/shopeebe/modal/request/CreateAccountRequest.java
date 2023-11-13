package com.vti.shopeebe.modal.request;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class CreateAccountRequest {

    @NotBlank(message = "Tên không được để trống")
    private String username;

    @NotBlank(message = "Password không được để trống")
    @Size(min = 6, max = 20, message = "password phải có 6-20 ký tự")
    private String password;

    private Date dateOfBirth;

    private String address;

    private String fullName;

    private String phoneNumber;

    private String email;

    private String facebook;

    private String information;


}
