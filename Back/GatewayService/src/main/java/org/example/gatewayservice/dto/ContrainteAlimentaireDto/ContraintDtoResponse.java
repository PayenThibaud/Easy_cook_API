package org.example.gatewayservice.dto.ContrainteAlimentaireDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContraintDtoResponse {
    private int id_ContraiteAlimentaire;
    private String nom;
}
