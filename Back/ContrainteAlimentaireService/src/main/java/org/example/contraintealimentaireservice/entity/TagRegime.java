package org.example.contraintealimentaireservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagRegime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "recette_id")
    private int recetteId;


    @Column(name = "contrainte_alimentaire_id")
    private int contrainteAlimentaireId;
}

