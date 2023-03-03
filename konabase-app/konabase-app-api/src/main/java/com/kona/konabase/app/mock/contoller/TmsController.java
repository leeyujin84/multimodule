package com.kona.konabase.app.mock.contoller;

import com.kona.konabase.app.mock.model.TmsKakaoSendModel;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/tms/kakaoSend")
public class TmsController {

  @PostMapping
  public TmsKakaoSendModel.ResponseDto postSample(@RequestBody TmsKakaoSendModel.RequestDto requestDto) throws Exception{

    log.debug("{}", requestDto);

    return TmsKakaoSendModel.ResponseDto.builder().build();
  }

}
