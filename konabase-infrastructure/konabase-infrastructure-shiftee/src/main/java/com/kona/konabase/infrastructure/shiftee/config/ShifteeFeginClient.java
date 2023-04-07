package com.kona.konabase.infrastructure.shiftee.config;

import com.kona.konabase.infrastructure.shiftee.model.AttendanceAllModel;
import com.kona.konabase.infrastructure.shiftee.service.ShifteeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

@Configuration
@EnableFeignClients
public class ShifteeFeginClient {


    @Bean
    ShifteeClient shifteeClient2(@Autowired ShifteeClient shifteeClient){

        return shifteeClient;
    }


}
