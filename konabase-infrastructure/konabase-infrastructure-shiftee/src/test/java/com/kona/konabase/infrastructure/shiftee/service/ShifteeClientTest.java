package com.kona.konabase.infrastructure.shiftee.service;

import com.kona.konabase.infrastructure.shiftee.model.AttendanceAllModel;
import com.kona.konabase.infrastructure.shiftee.model.AttendanceAllModel.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;

@Slf4j
@SpringBootTest
@EnableFeignClients(clients = ShifteeClient.class)
@EnableAutoConfiguration
class ShifteeClientTest {

  @Autowired
  ShifteeClient shifteeService;

  @Test
  void getAttendanceAll() {
    ResponseEntity<ResponseDto[]> response = shifteeService.getAttendanceAll(AttendanceAllModel.RequestDto.builder()
        .employee_numbers(new String[]{"2020111"})
        .from("2023-01-05T00:00:00+09:00")
        .to("2023-02-10T00:00:00+09:00")
        .build());

        log.info("{}", response.getBody());
        log.info(String.valueOf(response.getStatusCode()));
  }
}