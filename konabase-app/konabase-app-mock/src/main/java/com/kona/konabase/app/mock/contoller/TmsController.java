package com.kona.konabase.app.mock.contoller;

import com.kona.konabase.app.mock.model.TmsKakaoSendModel;
import com.kona.konabase.infrastructure.atlassian.model.AttendanceAllModel2;
import com.kona.konabase.infrastructure.atlassian.service.AtlassianClient;
import com.kona.konabase.infrastructure.shiftee.model.AttendanceAllModel;
import com.kona.konabase.infrastructure.shiftee.model.AttendanceAllModel.ResponseDto;
import com.kona.konabase.infrastructure.shiftee.service.ShifteeClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/tms/kakaoSend.do")
@EnableFeignClients(clients = {ShifteeClient.class, AtlassianClient.class})
@RequiredArgsConstructor
public class TmsController {

  private final ShifteeClient shifteeClient;

  private final AtlassianClient atlassianClient;

  @PostMapping
  public TmsKakaoSendModel.ResponseDto postSample(@RequestBody TmsKakaoSendModel.RequestDto requestDto) throws Exception{

    log.debug("{}", requestDto);

    return TmsKakaoSendModel.ResponseDto.builder().build();
  }

  @GetMapping
  public TmsKakaoSendModel.ResponseDto getSample(@ModelAttribute TmsKakaoSendModel.RequestDto requestDto) throws Exception{

    log.debug("{}", requestDto);

    ResponseEntity<ResponseDto[]> response = shifteeClient.getAttendanceAll(AttendanceAllModel.RequestDto.builder()
        .employee_numbers(new String[]{"2020111"})
        .from("2023-01-05T00:00:00+09:00")
        .to("2023-02-10T00:00:00+09:00")
        .build());

    log.debug("{}", response.getBody());


    ResponseEntity<AttendanceAllModel2.ResponseDto[]> response2 = atlassianClient.getAttendanceAll(AttendanceAllModel2.RequestDto.builder()
        .employee_numbers(new String[]{"2020111"})
        .from("2023-01-05T00:00:00+09:00")
        .to("2023-02-10T00:00:00+09:00")
        .build());

    log.debug("{}", response2.getBody());

    return TmsKakaoSendModel.ResponseDto.builder().build();
  }

  @PutMapping("/{id}")
  public TmsKakaoSendModel.ResponseDto putSample(@PathVariable("id")String id, @RequestBody TmsKakaoSendModel.RequestDto requestDto) throws Exception{

    log.debug("{} {}", id, requestDto);

    return TmsKakaoSendModel.ResponseDto.builder().build();
  }

}
