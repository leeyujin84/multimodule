package com.kona.konabase.core.common.config;


import static feign.Logger.Level.HEADERS;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kona.konabase.core.common.constant.RequestHeaderConstant;
import feign.Logger;
import feign.Request;
import feign.Response;
import feign.Util;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

@Slf4j
public class FeginLogging extends Logger {
  @Autowired
  ObjectMapper objectMapper;

  StopWatch stopWatch = new StopWatch();
  @Override
  protected void logRequest(String configKey, Level logLevel, Request request) {
    stopWatch.start();

    String requesterId = request.headers().get(RequestHeaderConstant.REQUESTER_ID).stream()
        .findFirst().orElse(RequestHeaderConstant.EMPTY_REQUESTER_ID);
    String requestBody = "";
    try {
      //json 일때만 info
      requestBody = objectMapper.readTree(request.body()).toString();
    } catch (Exception e) {

    } finally {
      log.info("[{}] REQ : {} {}{} {} {}"
          , requesterId
          , request.httpMethod()
          , request.url()
          , ""
          , getHeaders(request)
          , requestBody
      );
    }

    if (logLevel.ordinal() >= HEADERS.ordinal()) {
      super.logRequest(configKey, logLevel, request);
    }

  }

  @Override
  protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response,
      long elapsedTime)
      throws IOException {
    stopWatch.stop();

    String requesterId = response.request().headers().get(RequestHeaderConstant.REQUESTER_ID)
        .stream().findFirst().orElse(RequestHeaderConstant.EMPTY_REQUESTER_ID);

    if (logLevel.ordinal() >= HEADERS.ordinal()) {
      response = super.logAndRebufferResponse(configKey, logLevel, response, elapsedTime);
    }
    byte[] bodyData = Util.toByteArray(response.body().asInputStream());

    String responseBody = "";
    try {

      responseBody = objectMapper.readTree(bodyData).toString();
    } catch (Exception e) {
      responseBody = "not Json Body";
    } finally {
      log.info("[{}] RES : {} {} {}ms {}"
          , requesterId
          , response.request().url()
          , response.status()
          , stopWatch.getTotalTimeMillis()
          , responseBody
      );
    }

    return response.toBuilder().body(bodyData).build();
  }


  @Override
  protected void log(String configKey, String format, Object... args) {
    log.debug(format(configKey, format, args));
  }

  protected String format(String configKey, String format, Object... args) {
    return String.format(methodTag(configKey) + format, args);
  }

  private Map<String, String> getHeaders(Request request) {
    Map<String, String> headerMap = new HashMap<>();

    request.headers().keySet().stream().forEach(key -> {
      headerMap.put(key, request.headers().get(key).stream().findFirst().orElse(null));
    });
    return headerMap;
  }
}
