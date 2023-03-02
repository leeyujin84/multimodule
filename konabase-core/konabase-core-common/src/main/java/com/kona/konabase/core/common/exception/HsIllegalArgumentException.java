package com.kona.konabase.core.common.exception;

public class HsIllegalArgumentException extends IllegalArgumentException {
  public boolean logging;

  private HsIllegalArgumentException(String message, Exception ex, boolean logging) {
    super(message, ex);
    this.logging = logging;
  }

  public static HsIllegalArgumentException message(String message) {
    return message(message, false);
  }

  public static HsIllegalArgumentException message(String message, boolean logging) {
    return exception(message, null, logging);
  }

  public static HsIllegalArgumentException exception(String message) {
    return exception(message, null, false);
  }

  public static HsIllegalArgumentException exception(Exception e) {
    return exception(e, false);
  }

  public static HsIllegalArgumentException exception(Exception e, boolean logging) {
    return exception(null, e, logging);
  }

  public static HsIllegalArgumentException exception(String message, Exception e, boolean logging) {
    return new HsIllegalArgumentException(message, e, logging);
  }

}
