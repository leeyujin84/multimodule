package com.kona.konabase.core.common.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
public class BaseRequest {

  public String requesterId;

  public void updateRequesterId(String requesterId) {
    this.requesterId = requesterId;
  }
}
