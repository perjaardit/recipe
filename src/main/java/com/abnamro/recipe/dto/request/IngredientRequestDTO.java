package com.abnamro.recipe.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class IngredientRequestDTO {
    private final String name;
    private final String healthBenefits;
    private final Integer loop;
}
