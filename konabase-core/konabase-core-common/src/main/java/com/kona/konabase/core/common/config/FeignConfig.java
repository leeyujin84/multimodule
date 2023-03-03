package com.kona.konabase.core.common.config;


import com.kona.konabase.core.common.constant.RequestHeaderConstant;
import com.kona.konabase.core.common.filter.LoggingFilter;
import feign.Contract;
import feign.Logger.Level;
import feign.RequestInterceptor;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.AnnotatedParameterProcessor;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.util.ObjectUtils;

@Slf4j
public class FeignConfig {
  @Value("${application.component.name:UNKNOWN}")
  String requesterId;

  @Bean
  public RequestInterceptor requestInterceptor() {
    String traceId = ObjectUtils.isEmpty(MDC.get(RequestHeaderConstant.TRACE_ID))
        ? LoggingFilter.getTraceId()
        : MDC.get(RequestHeaderConstant.TRACE_ID);

    return requestTemplate -> {
      requestTemplate.header(RequestHeaderConstant.TRACE_ID,
          ObjectUtils.isEmpty(traceId) ? LoggingFilter.getTraceId() : traceId);
      requestTemplate.header(RequestHeaderConstant.REQUESTER_ID, requesterId);
    };
  }

  @Bean
  public FeginLogging CommonFeignRequestLogging() {
      return new FeginLogging();
  }

  @Bean
  public Level feignLoggerLevel() {
    return Level.BASIC;
  }

  @Bean
  Contract contract(@Autowired(required = false) List<AnnotatedParameterProcessor> parameterProcessors,
       ConversionService feignConversionService) {
    if (parameterProcessors == null) {
      parameterProcessors = new ArrayList<>();
    }
    return new SpringMvcContract(parameterProcessors, feignConversionService, false);
  }

}
