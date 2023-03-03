package com.kona.konabase.infrastructure.shiftee.config;

import com.kona.konabase.core.common.config.FeignConfig;
import feign.Contract;
import feign.Logger;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.AnnotatedParameterProcessor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
@EnableFeignClients
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
