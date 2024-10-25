package org.example.frigoservice.service.impl;

import org.example.frigoservice.dto.FrigoAlimentDto.FrigoAlimentDtoRequest;
import org.example.frigoservice.dto.FrigoAlimentDto.FrigoAlimentDtoResponse;
import org.example.frigoservice.service.FrigoAlimentService;

import java.util.List;

public class FrigoAlimentImplService extends FrigoAlimentService {
    @Override
    public FrigoAlimentDtoResponse addAlimentToFrigo(FrigoAlimentDtoRequest frigoAlimentDtoRequest) {
        return null;
    }

    @Override
    public boolean removeAlimentFromFrigo(int idFrigo, int idAliment) {
        return false;
    }

    @Override
    public FrigoAlimentDtoResponse updateAlimentQuantity(int idFrigo, int idAliment, int quantity) {
        return null;
    }

    @Override
    public List<FrigoAlimentDtoResponse> getAlimentsByFrigoId(int idFrigo) {
        return List.of();
    }
}
