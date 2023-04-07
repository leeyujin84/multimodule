package com.kona.konabase.infrastructure.shiftee.config;

import com.kona.konabase.core.common.config.FeignConfig;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@RequiredArgsConstructor
@Import(FeignConfig.class)
public class ShifteeFeginConfig {
    @Bean
    public RequestInterceptor custRequestInterceptor() {

        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.header("Accept", "application/json");
            requestTemplate.header("Authorization", "Bearer aLy3qz6PUUjn17fXAxUqcAyBP5fN6OuZmo178jECvklJmaHE0Lb0B1xXHhud");
        };
    }


}
