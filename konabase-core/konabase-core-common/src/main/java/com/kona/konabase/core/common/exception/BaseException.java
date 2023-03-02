package com.kona.konabase.core.common.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@Builder
public class BaseException extends RuntimeException {

  private HttpStatus status;
  private String reason;
  private String message;

  public BaseException(ExceptionCode baseExceptionCode) {
    this.status = baseExceptionCode.getStatus();
    this.reason = baseExceptionCode.getCode();
    this.message = baseExceptionCode.getMessage();
  }

  public BaseException(String reason, String message) {
    this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    this.reason = reason;
    this.message = message;
  }

  public BaseException(HttpStatus status, String reason, String message) {
    this.status = status;
    this.reason = reason;
    this.message = message;
  }
}
