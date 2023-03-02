package com.kona.konabase.core.common.exception;

public class HsApplicationException extends RuntimeException {
  public boolean logging = true;

  private HsApplicationException(String message, Exception ex, boolean logging) {
    super(message, ex);
    this.logging = logging;
  }

  public static HsApplicationException message(String message) {
    return message(message, true);
  }

  public static HsApplicationException message(String message, boolean logging) {
    return exception(message, null, logging);
  }

  public static HsApplicationException exception(Exception e) {
    return exception(e, true);
  }
  public static HsApplicationException exception(String message, Exception e) {
    return exception(message, e, true);
  }
  public static HsApplicationException exception(Exception e, boolean logging) {
    return exception(null, e, logging);
  }

  public static HsApplicationException exception(String message, Exception e, boolean logging) {
    return new HsApplicationException(message, e, logging);
  }

}

