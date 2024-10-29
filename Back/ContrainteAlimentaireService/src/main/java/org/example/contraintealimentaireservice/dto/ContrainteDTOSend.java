package org.example.contraintealimentaireservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ContrainteDTOSend {
    private int id_ContraiteAlimentaire;
    private String nom;
}
