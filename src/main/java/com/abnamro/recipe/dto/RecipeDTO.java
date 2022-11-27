package com.abnamro.recipe.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "The model view of the recipe")
public class RecipeDTO {

    @ApiModelProperty(value = "The id of the recipe")
    private Long rid;

    @ApiModelProperty(value = "The unique name of the recipe")
    private String name;

    @ApiModelProperty(value = "The instructions for a delicious  recipe")
    private String instructions;

    @ApiModelProperty(value = "Indicates for how many person is to be cooked")
    private Integer servings;

    @ApiModelProperty(value = "Indicates if the recipe is vegetarian")
    private Boolean vegetarian;

    @ApiModelProperty(value = "The ingredients necessary to prepare the recipe")
    private Set<IngredientDTO> ingredients;
}
