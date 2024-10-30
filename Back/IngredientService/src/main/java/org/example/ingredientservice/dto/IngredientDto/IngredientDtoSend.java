package org.example.ingredientservice.dto.IngredientDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class IngredientDtoSend {
    private int id_ingredient;
    private String nom;
    private int calories;
    private String allergens;
    private long barcode;
}
