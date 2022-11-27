package com.abnamro.recipe.service;

import com.abnamro.recipe.domain.Ingredient;
import com.abnamro.recipe.repository.IngredientRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class PrepareIngredientsService {

    private final IngredientRepository ingredientRepository;

    @PostConstruct
    public void populateIngredient() {
        ingredientRepository.saveAll(IntStream.range(1, 100)
                .mapToObj(value -> Ingredient.builder()
                        .createdDate(OffsetDateTime.now())
                        .healthBenefit("This are the benefits of the ingredient " + value)
                        .name("Ingredient " + value)
                        .build()).collect(Collectors.toList()));
    }
}
