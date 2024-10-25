package org.example.frigoservice.dto.FrigoDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.frigoservice.dto.FrigoAlimentDto.FrigoAlimentDtoResponse;
import org.example.frigoservice.entity.FrigoAliment;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrigoDtoResponse {
    private int idFrigo;
    private int idUtilisateur;
    private List<FrigoAlimentDtoResponse> alimentsDansLeFrigo;
}
