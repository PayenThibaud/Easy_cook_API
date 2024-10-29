package org.example.contraintealimentaireservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrefAlimUserDTOReceive {
    private int contrainteAlimentaireId;
    private int utilisateurId;
}
