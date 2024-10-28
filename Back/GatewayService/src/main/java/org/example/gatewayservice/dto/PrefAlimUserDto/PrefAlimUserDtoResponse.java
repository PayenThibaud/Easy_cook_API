package org.example.gatewayservice.dto.PrefAlimUserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrefAlimUserDtoResponse {
    private int id;
    private int contrainteAlimentaireId;
    private int utilisateurId;
}
