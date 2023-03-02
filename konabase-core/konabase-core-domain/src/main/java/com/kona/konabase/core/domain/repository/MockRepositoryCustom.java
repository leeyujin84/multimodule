package com.kona.konabase.core.domain.repository;

import com.kona.konabase.core.domain.model.MockModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MockRepositoryCustom {
    public List<MockModel.Mock> findAll(String a);
}
