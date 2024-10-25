package org.example.recetteservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class Recette {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_recette;
    private String nom;
    private Double calories;
    private String description;

    @ManyToMany
    private List<Ingredient> ingredients;

    private Boolean isVisible;

}