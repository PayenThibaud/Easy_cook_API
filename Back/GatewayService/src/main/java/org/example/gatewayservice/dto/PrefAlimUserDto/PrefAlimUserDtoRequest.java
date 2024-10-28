package org.example.gatewayservice.dto.PrefAlimUserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrefAlimUserDtoRequest {
    private int contrainteAlimentaireId;
    private int utilisateurId;
}
