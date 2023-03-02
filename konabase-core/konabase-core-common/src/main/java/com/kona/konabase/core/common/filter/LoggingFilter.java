package com.kona.konabase.core.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kona.konabase.core.common.constant.RequestHeaderConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StopWatch;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoggingFilter extends OncePerRequestFilter {

  private final ObjectMapper objectMapper;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws IOException {

    StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    //filterChain.doFilter(requestWrapper, responseWrapper) 이후 로그 출력할경우엔 ContentCachingRequestWrapper 사용.
//    ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
    CachedBodyHttpServletRequest requestWrapper = new CachedBodyHttpServletRequest(request);
    ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

    String traceId = ObjectUtils.isEmpty(requestWrapper.getHeader(RequestHeaderConstant.TRACE_ID))
        ? getTraceId()
        : requestWrapper.getHeader(RequestHeaderConstant.TRACE_ID);

    try {

      String requesterId =
          ObjectUtils.isEmpty(requestWrapper.getHeader(RequestHeaderConstant.REQUESTER_ID))
              ? "CLIENT"
              : requestWrapper.getHeader(RequestHeaderConstant.REQUESTER_ID);

      MDC.put(RequestHeaderConstant.TRACE_ID, traceId);
      MDC.put(RequestHeaderConstant.REQUESTER_ID, requesterId);

      String requestBody = "";
      try {
        //json 일때만 info
        requestBody = objectMapper.readTree(requestWrapper.getReader()).toString();
      } catch (Exception e) {

      } finally {
        log.info("[{}] REQ : {} {}{} {} {}"
            , requesterId
            , requestWrapper.getMethod()
            , requestWrapper.getRequestURL()
            , requestWrapper.getQueryString() == null ? "" : "?" + requestWrapper.getQueryString()
            , getHeaders(request)
            , requestBody
        );
      }

      filterChain.doFilter(requestWrapper, responseWrapper);

      stopWatch.stop();

      String responseBody = "";
      try {
        //json 일때만 info
        responseBody = objectMapper.readTree(responseWrapper.getContentAsByteArray()).toString();
      } catch (Exception e) {
        responseBody = "not Json Body";
      } finally {
        log.info("[{}] RES : {} {}ms {}"
            , requesterId
            , responseWrapper.getStatus()
            , stopWatch.getTotalTimeMillis()
            , responseBody
        );
      }


    } catch (Exception e) {
      log.error("Filter Exception {}", e);
    } finally {
      response.addHeader(RequestHeaderConstant.TRACE_ID, traceId);
      responseWrapper.copyBodyToResponse();
    }
  }

  private Map<String, String> getHeaders(HttpServletRequest request) {
    Map<String, String> headerMap = new HashMap<>();

    Enumeration<String> headerArray = request.getHeaderNames();
    while (headerArray.hasMoreElements()) {
      String headerName = headerArray.nextElement();
      headerMap.put(headerName, request.getHeader(headerName));
    }
    return headerMap;
  }
/*

  private Map<String, String> getQueryParameter(HttpServletRequest request) {
    Map<String, String> queryMap = new HashMap<>();
    request.getParameterMap()
        .forEach((key, value) -> queryMap.put(key, String.join("", value)));
    return queryMap;
  }

  private String contentBody(final byte[] contents) {
    StringBuilder sb = new StringBuilder("\n");
    BufferedReader bufferedReader = new BufferedReader(
        new InputStreamReader(new ByteArrayInputStream(contents)));
    bufferedReader.lines().forEach(str -> sb.append(str).append("\n"));
    sb.deleteCharAt(sb.length() - 1);
    return sb.toString();
  }
*/


  private static String getRandomHexString(int numchars) {
    Random r = new Random();
    StringBuffer sb = new StringBuffer();
    while (sb.length() < numchars) {
      sb.append(Integer.toHexString(r.nextInt()));
    }
    return sb.toString().substring(0, numchars);
  }

  public static String getTraceId() {
    StringBuffer sb = new StringBuffer();
    LocalDateTime dateTime = LocalDateTime.now();
    ZonedDateTime seoulTime = dateTime.atZone(ZoneId.of("Asia/Seoul"));
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");

    //yyMMddHHmmss-xxxxxx
    return sb.append(seoulTime.format(formatter))
        .append("-")
        .append(getRandomHexString(6)).toString();
  }


}
