package com.kona.konabase.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MockModel {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Mock{
        Long Id;
        String name;
        String grade;
        LocalDateTime created;
    }
}
