package com.kona.konabase.app.mock.service.impl;

import com.kona.konabase.app.mock.service.ShifteeService;
import com.kona.konabase.core.domain.mapper.MockMapper;
import com.kona.konabase.core.domain.model.MockModel;
import com.kona.konabase.core.domain.repository.MockRepository;
import com.kona.konabase.infrastructure.shiftee.model.AttendanceAllModel;
import com.kona.konabase.infrastructure.shiftee.service.ShifteeClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableFeignClients(clients = ShifteeClient.class)
public class ShifteeServiceImpl implements ShifteeService {

    private final MockRepository mockRepository;

    private final MockMapper mockMapper;

    private final ShifteeClient shifteeClient;


    @Override
    public List<MockModel.Mock> find() throws Exception {
        return mockRepository.findAll("a");
    }

    @Override
    public List<AttendanceAllModel.ResponseDto> find2() throws Exception {
        ResponseEntity<AttendanceAllModel.ResponseDto[]> responseEntity =  shifteeClient.getAttendanceAll(AttendanceAllModel.RequestDto.builder()
                .employee_numbers(new String[]{"2020111"})
//                .from("2023-01-05T00:00:00%2B09:00")
//                .to("2023-01-10T00:00:00%2B09:00")
                .from("2023-01-05T00:00:00+09:00")
                .to("2023-02-10T00:00:00+09:00")
                .build());
        return Arrays.asList(responseEntity.getBody());
    }


}
