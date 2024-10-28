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
    private String ingredients;
    private String regime;
    private double cout;
    private Boolean isVisible;
}
