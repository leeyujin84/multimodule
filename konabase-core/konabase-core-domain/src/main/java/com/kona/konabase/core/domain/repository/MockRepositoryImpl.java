package com.kona.konabase.core.domain.repository;

import com.kona.konabase.core.domain.entity.QMockEntity;
import com.kona.konabase.core.domain.model.MockModel;
import com.kona.konabase.core.domain.repository.MockRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MockRepositoryImpl implements MockRepositoryCustom {
    private final JPAQueryFactory queryFactory;


    QMockEntity mockEntity = QMockEntity.mockEntity;

    @Override
    public List<MockModel.Mock> findAll(String a) {
        return queryFactory
            .select(
                    Projections.constructor(MockModel.Mock.class
                            ,mockEntity.id
                            ,mockEntity.name
                            ,mockEntity.grade
                            ,mockEntity.created
                    )
            )
            .from(mockEntity)
            .fetch();

    }
}
