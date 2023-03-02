package com.kona.konabase.infrastructure.shiftee.config;

import feign.Contract;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.AnnotatedParameterProcessor;
import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;

import java.util.ArrayList;
import java.util.List;

public class FeginConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {

        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.header("Accept", "application/json");
            requestTemplate.header("Authorization", "Bearer aLy3qz6PUUjn17fXAxUqcAyBP5fN6OuZmo178jECvklJmaHE0Lb0B1xXHhud");

        };
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public FeignFormatterRegistrar localDateFeignFormatterRegister() {
        return registry -> {
            DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
            registrar.setUseIsoFormat(true);
            registrar.registerFormatters(registry);
        };
    }

    @Bean
    Contract contract(@Autowired(required = false) List<AnnotatedParameterProcessor> parameterProcessors,
                      ConversionService feignConversionService) {
        if (parameterProcessors == null) {
            parameterProcessors = new ArrayList<>();
        }
        return new SpringMvcContract(parameterProcessors, feignConversionService, false);
    }

//    @Bean
//    public FeginLogging customFeignRequestLogging() {
//        return new FeginLogging();
//    }
}
