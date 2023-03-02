package com.kona.konabase.core.common.exception;

import org.springframework.http.HttpStatus;

/**
 * org.springframework.http.HttpStatus 값에 따라 적당하게 설정
 * APP 마다 별도 정의할경우 ExceptionCode 인터페이스 구현하여 정의
 * response Code조합
 */
public enum BaseExceptionCode implements ExceptionCode {
  /** BAD_REQUEST, "400_10000", "잘못된 요청입니다." */
  BEC_400_10000(HttpStatus.BAD_REQUEST, "400_10000", "잘못된 요청입니다."),
  /** BAD_REQUEST, "400_10001", "유효하지 않은 x-requester-id 입니다." */
  BEC_400_10001(HttpStatus.BAD_REQUEST, "400_10001", "유효하지 않은 x-requester-id 입니다."),
  /** BAD_REQUEST, "400_10002", "필수 입력 항목입니다. (%s)" */
  BEC_400_10002(HttpStatus.BAD_REQUEST, "400_10002", "필수 입력 항목입니다. (%s)"),

  /** NOT_FOUND, "404_10000", "존재하지 않는 정보입니다." */
  BEC_404_10000(HttpStatus.NOT_FOUND, "404_10000", "존재하지 않는 정보입니다."),

  /** CONFLICT, "409_10000", "이미 사용 중 입니다." */
  BEC_409_10000(HttpStatus.CONFLICT, "409_10000", "이미 사용 중 입니다."),

  /** UNPROCESSABLE_ENTITY, "422_10000", "Invalid Parameter Value" */
  BEC_422_10000(HttpStatus.UNPROCESSABLE_ENTITY, "422_10000", "Invalid Parameter Value"),

  /** INTERNAL_SERVER_ERROR, "500_10000", "서버에 내부 오류가 발생했습니다. 요청을 다시 시도하세요." */
  BEC_500_10000(HttpStatus.INTERNAL_SERVER_ERROR, "500_10000", "서버에 내부 오류가 발생했습니다. 요청을 다시 시도하세요."),
  ;


  private HttpStatus status;
  private String code;
  private String message;

  BaseExceptionCode(final HttpStatus status, final String code, final String message) {
    this.status = status;
    this.message = message;
    this.code = code;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
