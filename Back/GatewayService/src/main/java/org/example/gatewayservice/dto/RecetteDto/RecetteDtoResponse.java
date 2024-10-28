package org.example.gatewayservice.dto.RecetteDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecetteDtoResponse {
    private int id_recette;
    private String nom;
    private String description;
    private int tempsPreparation;
    private int nombreCalories;
    private List<Integer> ingredients;
    private double cout;
    private Boolean isVisible;
    private Boolean isFavorite;
}
