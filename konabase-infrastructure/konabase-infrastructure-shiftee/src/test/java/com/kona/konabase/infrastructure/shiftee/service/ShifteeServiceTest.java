package com.kona.konabase.infrastructure.shiftee.service;

import com.kona.konabase.infrastructure.shiftee.model.AttendanceAllModel;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;

/*@SpringBootTest(
        classes = {
                ShifteeServiceTest.FakeFeignConfiguration.class,
        },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)*/

@Slf4j
@SpringBootTest
@EnableFeignClients(clients = ShifteeClient.class)
@EnableAutoConfiguration
class ShifteeServiceTest {

    @Autowired
    ShifteeClient shifteeService;

    @Test
    void getAttendanceAll() {
        ResponseEntity<AttendanceAllModel.ResponseDto[]> response = shifteeService.getAttendanceAll(AttendanceAllModel.RequestDto.builder()
                .employee_numbers(new String[]{"2020111"})
//                .from("2023-01-05T00:00:00%2B09:00")
//                .to("2023-01-10T00:00:00%2B09:00")
                .from("2023-01-05T00:00:00+09:00")
                .to("2023-02-10T00:00:00+09:00")
                .build());


        log.info("{}", response.getBody());
        log.info(String.valueOf(response.getStatusCode()));
    }

  /*  @Configuration(proxyBeanMethods = false)
    static class FakeRibbonConfiguration {

        @LocalServerPort
        int port;

        @Bean
        public ServerList<Server> serverList() {
            return new StaticServerList<>(new Server("localhost", port));
        }
    }

    @Configuration(proxyBeanMethods = false)
    @EnableFeignClients(clients = ShifteeService.class)
    @EnableAutoConfiguration
    @RibbonClient(
            name = "messagingRestClient",
            configuration = ShifteeServiceTest.FakeRibbonConfiguration.class)
    static class FakeFeignConfiguration {}*/

}