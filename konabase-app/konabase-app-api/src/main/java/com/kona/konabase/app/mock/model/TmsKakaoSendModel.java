package com.kona.konabase.app.mock.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TmsKakaoSendModel {

  @Getter
  @Builder
  @AllArgsConstructor(staticName = "of")
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public static class RequestDto{
    @Builder.Default
    public String billCode="20110175";
    @Builder.Default
    public String senderKey="7f6915dc6afc8cf9e9af9fea0c86e94418755dc1";
    @Builder.Default
    public String inputSys="CIS";
    public String deptName;
    public String fromTel;
    public String resendType;
    public String toMSG;
    public String templateCode;
    public String toHP;
  }

  @Getter
  @Builder
  @AllArgsConstructor(staticName = "of")
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public static class ResponseDto {

    private String returnMSG;
    private String returnCode;
    private String etc5;
    private String etc4;
    private String resendMtMessageReuse;
    private String resendMtTo;
    private String resendMtFrom;
    private String resendMtType;
    private String billCode;
    private String message;
    private String templateCode;
    private String phoneNumber;
    private String senderKey;
    private String priority;
    private String status;
    private String inputSys;
    private String deptName;
    private String fromTel;
    private String resendType;
    private String toMSG;
    private String toHP;
  }

}
