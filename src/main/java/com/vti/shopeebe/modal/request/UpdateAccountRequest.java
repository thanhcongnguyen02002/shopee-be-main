package com.vti.shopeebe.modal.request;

import com.vti.shopeebe.modal.entity.Role;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateAccountRequest {

    private Role role;

    private String password;

    private Date dateOfBirth;

    private String address;

    private String fullName;

    private String phoneNumber;

    private String email;

    private String facebook;

    private String information;
}
