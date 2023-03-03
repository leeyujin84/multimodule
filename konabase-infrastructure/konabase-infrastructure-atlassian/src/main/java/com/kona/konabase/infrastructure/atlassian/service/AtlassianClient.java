package com.kona.konabase.infrastructure.atlassian.service;


import com.kona.konabase.infrastructure.atlassian.config.AtlassianFeginConfig;
import com.kona.konabase.infrastructure.atlassian.model.AttendanceAllModel2;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name="AtlassianClient", url="https://shiftee.io", configuration = AtlassianFeginConfig.class)
public interface AtlassianClient {

    @GetMapping(value = "/openapi/attendance/all", produces = "application/json", consumes = "application/json")
    ResponseEntity<AttendanceAllModel2.ResponseDto[]> getAttendanceAll(@SpringQueryMap AttendanceAllModel2.RequestDto requestDto);
}
