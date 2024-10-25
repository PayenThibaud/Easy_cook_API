package org.example.frigoservice.dto.FrigoDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.frigoservice.dto.FrigoAlimentDto.FrigoAlimentDtoRequest;
import org.example.frigoservice.entity.FrigoAliment;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrigoDtoRequest {
    private int idUtilisateur;
    private List<FrigoAlimentDtoRequest> alimentsDansLeFrigo;
}
