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
public class PrefAlimUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "user_id")
    private int utilisateurId;


    @Column(name = "contrainte_alimentaire_id")
    private int contrainteAlimentaireId;
}
