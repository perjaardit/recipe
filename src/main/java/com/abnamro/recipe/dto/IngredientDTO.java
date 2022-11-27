package com.abnamro.recipe.dto;

import com.abnamro.recipe.dto.enumeration.MeasureUnit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "The model view of the ingredient")
public class IngredientDTO {

    @ApiModelProperty(value = "The id of the ingredient recipe")
    private Long id;

    @ApiModelProperty(value = "The id of the ingredient")
    private Long iid;

    @ApiModelProperty(value = "The unique name of the ingredient")
    private String name;

    @ApiModelProperty(value = "The health benefits of the ingredient")
    private String healthBenefit;

    @ApiModelProperty(value = "The measure of the ingredient")
    private Double measure;

    @ApiModelProperty(value = "The unit of ingredient's measure")
    private MeasureUnit unit;
}
