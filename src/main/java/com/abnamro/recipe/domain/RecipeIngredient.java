package com.abnamro.recipe.domain;

import com.abnamro.recipe.domain.enumeration.IngredientUnit;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class RecipeIngredient {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "iid", referencedColumnName = "iid")
    private Ingredient ingredient;

    @Column
    private Double measure;

    @Column
    @Enumerated(EnumType.STRING)
    private IngredientUnit unit;

}
