package org.example.recetteservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
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
    private String description;
    private int tempsPreparation;
    private int nombreCalories;
    private List<Integer> ingredients;
    private double cout;
    private Boolean isVisible;
    private Boolean isFavorite;

    @ManyToMany
    @JoinTable(
            name = "recette_ingredient",
            joinColumns = @JoinColumn(name = "recette_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;

}