package org.example.gatewayservice.dto.IngredientDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDtoResponse {
    private int id_ingredient;
    private String nom;
}
