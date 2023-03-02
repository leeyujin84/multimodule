package com.kona.konabase.core.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.kona.konabase.core.common.constant.RequestHeaderConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.slf4j.MDC;


@Getter
@Builder
@AllArgsConstructor(staticName = "of")
public class BaseErrorResponse<T> {

  @Builder.Default
  private String reason = "000";
  @Builder.Default
  private String message = "";
  @Builder.Default
  private String traceId = MDC.get(RequestHeaderConstant.TRACE_ID);

  @JsonInclude(Include.NON_EMPTY)
  private T data;

  public BaseErrorResponse(T data) {
    this.reason = "000";
    this.message = "";
    this.traceId = MDC.get(RequestHeaderConstant.TRACE_ID);
    this.data = data;
  }

  @Getter
  @Builder
  @AllArgsConstructor(staticName = "of")
  public static class BindError {

    String field;
    String type;
    String message;
  }
}

