package com.abnamro.recipe.domain;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Ingredient {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iid;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdDate;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "longtext")
    private String healthBenefit;
}