package com.kona.konabase.core.domain.mapper;

import com.kona.konabase.core.domain.model.MockModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MockMapper {
    List<MockModel.Mock> findAll();
}
