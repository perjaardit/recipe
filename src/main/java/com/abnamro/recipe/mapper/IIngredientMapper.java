package com.abnamro.recipe.mapper;

import com.abnamro.recipe.domain.RecipeIngredient;
import com.abnamro.recipe.dto.IngredientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.Set;

@Mapper(uses = {UnitMapper.class})
public interface IIngredientMapper {

    @Mapping(target = "iid", source = "ingredient.iid")
    IngredientDTO mapToDTO(final RecipeIngredient recipe);

    @Mapping(target = "ingredient.iid", source = "iid")
    RecipeIngredient mapToDomain(final IngredientDTO recipe);

    @Mapping(target = "ingredient.iid", source = "iid")
    Set<RecipeIngredient> mapToDomains(final Set<IngredientDTO> recipes);

    @Mappings({
            @Mapping(source = "ingredientRequest.measure", target = "measure"),
            @Mapping(source = "ingredientRequest.unit", target = "unit")
    })
    RecipeIngredient map(final IngredientDTO ingredientRequest, @MappingTarget RecipeIngredient recipeIngredient);

}
