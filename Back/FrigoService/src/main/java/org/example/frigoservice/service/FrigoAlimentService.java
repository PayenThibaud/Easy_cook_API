package org.example.frigoservice.service;

import org.example.frigoservice.dto.FrigoAlimentDto.FrigoAlimentDtoRequest;
import org.example.frigoservice.dto.FrigoAlimentDto.FrigoAlimentDtoResponse;

import java.util.List;

public interface FrigoAlimentService {
    FrigoAlimentDtoResponse addAlimentToFrigo(FrigoAlimentDtoRequest frigoAlimentDtoRequest);
    boolean removeAlimentFromFrigo(int idFrigo, int idAliment);
    FrigoAlimentDtoResponse updateAlimentQuantity(int idFrigo, int idAliment, int quantity);
    List<FrigoAlimentDtoResponse> getAlimentsByFrigoId(int idFrigo);
}