package com.vti.shopeebe.config.jwt;

import com.alibaba.fastjson.JSON;
import com.vti.shopeebe.exception.AppException;
import com.vti.shopeebe.modal.dto.LoginDto;
import com.vti.shopeebe.modal.entity.Role;
import com.vti.shopeebe.repository.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Slf4j
@Component
public class JWTTokenUtils {

    private static final long EXPIRATION_TIME = 864000000; // 10 ngày: thời hạn của token
        private static final String SECRET = "123456";
    private static final String PREFIX_TOKEN = "Bearer";
        private static final String AUTHORIZATION = "Authorization";

    @Autowired
    private TokenRepository tokenRepository;

    // Hàm này dùng để tạo ra token
    public String createAccessToken(LoginDto loginDto) {
        // Tạo giá trị thời hạn token ( bằng thời gian hiện tại + 10 ngày hoặc tùy )
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        String token = Jwts.builder()
                .setId(String.valueOf(loginDto.getId())) // Sét giá trị Id
                .setSubject(loginDto.getUsername()) // Sét giá trị subject
                .setIssuedAt(new Date())
                .setIssuer("VTI")
                .setExpiration(expirationDate) // Sét thời hạn của token
                .signWith(SignatureAlgorithm.HS512, SECRET) // Khai báo phương thức mã hóa Token và chữ kí bí mật
                .claim("authorities", loginDto.getRole().name()) // Thêm trường authorities để lưu giá trị phân quyền
                .claim("user_agent", loginDto.getUserAgent()).compact(); // thêm trường user_agent để lưu thông tin trình duyệt đang dùng
        
        return token;
    }

    // Hàm dùng để giải mã hóa Token
    public LoginDto parseAccessToken(String token) {
        LoginDto loginDto = new LoginDto();
        if (!token.isEmpty()) {
            try {
                token = token.replace(PREFIX_TOKEN, "").trim();
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token).getBody();
                // Lấy ra các thông tin
                String user = claims.getSubject();
                Role role = Role.valueOf(claims.get("authorities").toString());
                String userAgent = claims.get("user_agent").toString();

                // Gán các thông tin vào đối tượng LoginDto, có thể sử dụng Constructor
                loginDto.setUsername(user);
                loginDto.setRole(role);
                loginDto.setUserAgent(userAgent);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return loginDto;
    }

    public boolean checkToken(String token, HttpServletResponse response, HttpServletRequest httpServletRequest) {
        try {
            if (StringUtils.isBlank(token) || !token.startsWith(PREFIX_TOKEN)) { // Token bị trống -> lỗi
                responseJson(response, new AppException("Token không hợp lệ", 401, httpServletRequest.getRequestURI()));
                return false;
            }
        } catch (Exception e) {
            responseJson(response, new AppException(e.getMessage(), 401, httpServletRequest.getRequestURI()));
            return false;
        }
        return true;
    }

    // Hàm dùng để response dữ liệu khi gặp lỗi
    private void responseJson(HttpServletResponse response, AppException appException) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(appException.getCode());
        try {
            response.getWriter().print(JSON.toJSONString(appException));
        } catch (IOException e) {
            log.debug(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
