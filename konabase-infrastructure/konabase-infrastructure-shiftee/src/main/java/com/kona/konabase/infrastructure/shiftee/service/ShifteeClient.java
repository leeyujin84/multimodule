package com.kona.konabase.infrastructure.shiftee.service;


import com.kona.konabase.infrastructure.shiftee.config.ShifteeFeginConfig;
import com.kona.konabase.infrastructure.shiftee.model.AttendanceAllModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@FeignClient(name="shiftee", url="https://shiftee.io", configuration = ShifteeFeginConfig.class)
public interface ShifteeClient {

    //    @RequestMapping(method = RequestMethod.GET, value = "/shiftee", produces = "application/json", consumes = "application/json")
//    @GetMapping("/shiftee")
//    @RequestLine(value = "GET /shiftee", decodeSlash = false)
    @GetMapping(value = "/openapi/attendance/all", produces = "application/json", consumes = "application/json")
    ResponseEntity<AttendanceAllModel.ResponseDto[]> getAttendanceAll(@SpringQueryMap AttendanceAllModel.RequestDto requestDto);
}
