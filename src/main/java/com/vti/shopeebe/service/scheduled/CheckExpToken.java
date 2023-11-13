package com.vti.shopeebe.service.scheduled;

import com.vti.shopeebe.modal.entity.Token;
import com.vti.shopeebe.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@EnableAsync
@Component
@RequiredArgsConstructor
@Slf4j
public class CheckExpToken {

    private static final long EXPIRATION_TIME = 864000000; // 10 ngày: thời hạn của token

    @Autowired
    private TokenRepository tokenRepository;

    @Scheduled(cron = "0 0/1 * * * *")
    public void checkExpTokenJob() {
        log.info("Time to run log: {}", new Date());
        List<Token> tokensExp = tokenRepository.findAllByExpirationIsAfter(new Date(System.currentTimeMillis() + EXPIRATION_TIME));
        tokenRepository.deleteAll(tokensExp);
        log.info("Number token to delete: {}", tokensExp.size());
    }
}
