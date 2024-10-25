package org.example.frigoservice.entity;

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

public class Frigo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_frigo;
    private int id_utilisateur;

}
