package com.kona.konabase.core.common.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignHeaderConfig {
  @Value("${application.component.name:UNKNOWN}")
  String requesterId;
/*
  @Bean
  public RequestInterceptor requestInterceptor() {
    String traceId = MDC.get(RequestHeaderConstant.TRACE_ID);
    return requestTemplate -> {
      requestTemplate.header(RequestHeaderConstant.TRACE_ID,
          ObjectUtils.isEmpty(traceId) ? LoggingFilter.getTraceId() : traceId);
      requestTemplate.header(RequestHeaderConstant.REQUESTER_ID, requesterId);
    };
  }
  */


}
