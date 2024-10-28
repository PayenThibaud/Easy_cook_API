package org.example.contraintealimentaireservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrefAlimUserDTOSend {
    private int id;
    private int contrainteAlimentaireId;
    private int utilisateurId;
}
