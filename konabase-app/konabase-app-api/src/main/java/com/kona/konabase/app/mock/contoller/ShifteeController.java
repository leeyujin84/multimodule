package com.kona.konabase.app.mock.contoller;

import com.kona.konabase.app.mock.service.ShifteeService;
import com.kona.konabase.core.domain.model.MockModel;
import com.kona.konabase.infrastructure.shiftee.model.AttendanceAllModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/shiftee")
public class ShifteeController {

    private final ShifteeService shifteeService;

    @GetMapping
    public List<MockModel.Mock> get() throws Exception{
        return shifteeService.find();

    }

    @GetMapping("/2")
    public List<AttendanceAllModel.ResponseDto> get2() throws Exception{
        return shifteeService.find2();

    }
}
