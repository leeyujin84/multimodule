package com.kona.konabase.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name="MOCK")
public class MockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="GRADE")
    private String grade;


    @CreatedDate
    @Column(name="CREATED", updatable = false)
    private LocalDateTime created;


    public void updateGrade(String grade){
        this.grade = grade;
    }
}
