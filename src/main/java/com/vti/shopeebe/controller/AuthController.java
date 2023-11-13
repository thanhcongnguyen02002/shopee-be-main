package com.vti.shopeebe.controller;

import com.vti.shopeebe.config.jwt.JWTTokenUtils;
import com.vti.shopeebe.exception.AppException;
import com.vti.shopeebe.exception.ErrorResponseBase;
import com.vti.shopeebe.modal.dto.LoginDto;
import com.vti.shopeebe.modal.entity.Account;
import com.vti.shopeebe.modal.request.LoginRequest;
import com.vti.shopeebe.repository.AccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin("*")
@Validated
public class AuthController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JWTTokenUtils jwtTokenUtils;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    HttpServletRequest httpServletRequest;

    @PostMapping("/login-v2")
    public LoginDto loginJWT(@RequestBody LoginRequest request) {
        // Tìm kiếm xem user có tồn tại trong hệ thống hay không
        Optional<Account> accountOptional = accountRepository.findByUsername(request.getUsername());
        if (accountOptional.isEmpty()) {
            throw new AppException(ErrorResponseBase.LOGIN_FAILS_USERNAME);
        }
        // Kiểm tra xem password người dùng truyền vào có đúng hay không
        if (!encoder.matches(request.getPassword(), accountOptional.get().getPassword())) {
            // Nếu không khớp password bắn ra lỗi
            throw new AppException(ErrorResponseBase.LOGIN_FAILS_PASSWORD);
        }
        // Tạo đối tượng LoginDto để trả về
        LoginDto loginDto = new LoginDto();
        BeanUtils.copyProperties(accountOptional.get(), loginDto);
        loginDto.setUserAgent(httpServletRequest.getHeader("User-Agent")); // Lấy thông tin trình duyệt đang sử dụng
        String token = jwtTokenUtils.createAccessToken(loginDto); //  Tạo token
        loginDto.setToken(token); // Sét giá trị token vào loginDto để trả về cho người dùng sử dụng
        return loginDto;
    }

    @PostMapping("/login")
    public LoginDto loginBasicV1(Principal principal){ // Đối tượng principal có chứa thông tin username
    // Khi qua bước Authen sẽ tạo ra đối tượng Principal, tại controller sẽ sử dụng lại giá trị của dữ liệu này
        String username = principal.getName();
        Optional<Account> accountOptional = accountRepository.findByUsername((username));
        if (accountOptional.isEmpty()) {
            throw new AppException(ErrorResponseBase.LOGIN_FAILS);
        }
        LoginDto loginDto = new LoginDto();
        BeanUtils.copyProperties(accountOptional.get(), loginDto);
        return loginDto;
    }

}
