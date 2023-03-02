package com.kona.konabase.infrastructure.shiftee.service;


import com.kona.konabase.infrastructure.shiftee.config.FeginConfig;
import com.kona.konabase.infrastructure.shiftee.model.AttendanceAllModel;
import feign.QueryMap;
import feign.RequestLine;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@FeignClient(name="shiftee", url="https://shiftee.io", configuration = FeginConfig.class)
//@FeignClient(name="shiftee", url="http://localhost:38080", configuration = FeginConfig.class)
public interface ShifteeClient {

    @GetMapping(value = "/openapi/attendance/all", produces = "application/json", consumes = "application/json")
//    @RequestMapping(method = RequestMethod.GET, value = "/shiftee", produces = "application/json", consumes = "application/json")
//    @GetMapping("/shiftee")
//    @RequestLine(value = "GET /shiftee", decodeSlash = false)
    ResponseEntity<AttendanceAllModel.ResponseDto[]> getAttendanceAll(@SpringQueryMap AttendanceAllModel.RequestDto requestDto);
}
