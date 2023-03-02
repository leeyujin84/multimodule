package com.kona.konabase.app.mock.service;

import com.kona.konabase.core.domain.model.MockModel;
import com.kona.konabase.infrastructure.shiftee.model.AttendanceAllModel;

import java.util.List;

public interface ShifteeService {
    public List<MockModel.Mock> find() throws Exception;
    public List<AttendanceAllModel.ResponseDto> find2() throws Exception;


}
