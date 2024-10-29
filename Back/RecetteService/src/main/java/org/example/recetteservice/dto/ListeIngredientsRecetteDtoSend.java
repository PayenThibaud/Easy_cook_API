package org.example.recetteservice.dto;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ListeIngredientsRecetteDtoSend {
    private int ingredientIds;
    private int regimeIds;
    private double quantite;
}
