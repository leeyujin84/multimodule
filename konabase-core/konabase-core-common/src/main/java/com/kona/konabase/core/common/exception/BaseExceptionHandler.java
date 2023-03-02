package com.kona.konabase.core.common.exception;

import com.kona.konabase.core.common.model.BaseErrorResponse;
import com.kona.konabase.core.common.model.BaseErrorResponse.BindError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class BaseExceptionHandler extends RuntimeException {

  @Value("${hanssem.component.code:999}")
  String component;

  //@Valid
  @ExceptionHandler(value = {BindException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  protected ResponseEntity<BaseErrorResponse> handleBindException(BindException e) {
    BindingResult bindingResult = e.getBindingResult();
    BaseErrorResponse response = BaseErrorResponse.builder()
        .message(BaseExceptionCode.BEC_422_10000.getMessage())
        .reason(new StringBuffer().append(component).append("_")
            .append(BaseExceptionCode.BEC_422_10000.getCode()).toString())
        .data(bindingResult.hasErrors() ? bindingResult.getFieldErrors().stream().map(p ->
                BindError.of(p.getCode(), p.getField(), p.getDefaultMessage()))
            .collect(Collectors.toList()) : null)
        .build();
    log.error("BindException : ", e);
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  /*Request Parser 관련 오류*/
  @ExceptionHandler(value = {HttpMessageNotReadableException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  protected ResponseEntity<BaseErrorResponse> handleHttpMessageNotReadableException(Exception e) {
    BaseErrorResponse response = BaseErrorResponse.builder()
        .reason(new StringBuffer().append(component).append("_")
            .append(BaseExceptionCode.BEC_400_10000.getCode()).toString())
//        .message(e.getLocalizedMessage()) //e.message 를 내려주면 호출하는 사람이 인지 하나.... 보안상 좋지않은...
        .message(BaseExceptionCode.BEC_400_10000.getMessage()) //보안상.... 공통 메시지로 내려야 할 경우.
        .build();
    log.error("HttpMessageNotReadableException : ", e);
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  /*404, MethodNotSupported*/
  @ExceptionHandler(value = {NoHandlerFoundException.class,
      HttpRequestMethodNotSupportedException.class})
  protected ResponseEntity<BaseErrorResponse> handleNoHandlerFoundException(Exception e) {
    BaseErrorResponse response = BaseErrorResponse.builder()
        .reason(new StringBuffer().append(component).append("_")
            .append(HttpStatus.NOT_FOUND.value()).toString())
        .message(e.getMessage())
        .build();
    log.error("NoHandlerFoundException : {}", e.getMessage());
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  /*서버 오류*/
  @ExceptionHandler(value = Exception.class)
  protected ResponseEntity<BaseErrorResponse> handleException(Exception e) {
    BaseErrorResponse response = BaseErrorResponse.builder()
        .reason(new StringBuffer().append(component).append("_")
            .append(BaseExceptionCode.BEC_500_10000.getCode()).toString())
        .message(BaseExceptionCode.BEC_500_10000.getMessage())
        .build();
    log.error("Exception : ", e);
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }


  /* BaseException Error Handler */
  @ExceptionHandler(value = BaseException.class)
  protected ResponseEntity<BaseErrorResponse> handleBaseException(BaseException e) {
    BaseErrorResponse response = BaseErrorResponse.builder()
        .message(e.getMessage())
        .reason(new StringBuffer().append(component).append("_").append(e.getReason()).toString())
        .build();
    log.error("BaseException :", e);
    HttpStatus res = e.getStatus();
    return new ResponseEntity<>(response, res);
  }
}
