package com.kona.konabase.core.domain.repository;

import com.kona.konabase.core.domain.entity.MockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MockRepository extends JpaRepository<MockEntity, Long> {
    Optional<MockEntity> findById(Long aLong);

    List<MockEntity> findAll();

}
