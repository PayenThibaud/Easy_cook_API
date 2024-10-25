package org.example.frigoservice.service.impl;

import org.example.frigoservice.dto.FrigoAlimentDto.FrigoAlimentDtoRequest;
import org.example.frigoservice.dto.FrigoAlimentDto.FrigoAlimentDtoResponse;
import org.example.frigoservice.dto.FrigoDto.FrigoDtoResponse;
import org.example.frigoservice.entity.Frigo;
import org.example.frigoservice.entity.FrigoAliment;
import org.example.frigoservice.repository.FrigoAlimentRepository;
import org.example.frigoservice.repository.FrigoRepository;
import org.example.frigoservice.service.FrigoAlimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FrigoAlimentImplService implements FrigoAlimentService {

    @Autowired
    private FrigoAlimentRepository frigoAlimentRepository;

    @Autowired
    private FrigoRepository frigoRepository;

    @Override
    public FrigoAlimentDtoResponse addAlimentToFrigo(FrigoAlimentDtoRequest frigoAlimentDtoRequest) {
        Optional<Frigo> frigo = frigoRepository.findById(frigoAlimentDtoRequest.getIdFrigo());
        if (frigo.isEmpty()) {
            throw new IllegalArgumentException("Frigo not found");
        }

        FrigoAliment frigoAliment = FrigoAliment.builder()
                .frigo(frigo.get())
                .id_aliment(frigoAlimentDtoRequest.getIdAliment())
                .quantity(frigoAlimentDtoRequest.getQuantity())
                .build();

        frigoAliment = frigoAlimentRepository.save(frigoAliment);

        return new FrigoAlimentDtoResponse(
                frigoAliment.getId_frigoAliment(),
                frigoAlimentDtoRequest.getIdFrigo(),
                frigoAliment.getId_aliment(),
                frigoAliment.getQuantity()
        );
    }

    @Override
    public boolean removeAlimentFromFrigo(int idFrigo, int idAliment) {
        Optional<FrigoAliment> frigoAliment = frigoAlimentRepository.findByFrigoIdAndIdAliment(idFrigo, idAliment);
        if (frigoAliment.isPresent()) {
            frigoAlimentRepository.delete(frigoAliment.get());
            return true;
        }
        return false;
    }

    @Override
    public FrigoAlimentDtoResponse updateAlimentQuantity(int idFrigo, int idAliment, int quantity) {
        Optional<FrigoAliment> frigoAlimentOpt = frigoAlimentRepository.findByFrigoIdAndIdAliment(idFrigo, idAliment);
        if (frigoAlimentOpt.isPresent()) {
            FrigoAliment frigoAliment = frigoAlimentOpt.get();
            frigoAliment.setQuantity(quantity);
            frigoAliment = frigoAlimentRepository.save(frigoAliment);

            return new FrigoAlimentDtoResponse(
                    frigoAliment.getId_frigoAliment(),
                    new FrigoDtoResponse(frigoAliment.getFrigo().getId_frigo(), frigoAliment.getFrigo().getId_utilisateur(), null),
                    frigoAliment.getId_aliment(),
                    frigoAliment.getQuantity()
            );
        }
        throw new IllegalArgumentException("FrigoAliment not found");
    }

    @Override
    public List<FrigoAlimentDtoResponse> getAlimentsByFrigoId(int idFrigo) {
        List<FrigoAliment> aliments = frigoAlimentRepository.findAllByFrigoId(idFrigo);
        return aliments.stream()
                .map(frigoAliment -> new FrigoAlimentDtoResponse(
                        frigoAliment.getId_frigoAliment(),
                        new FrigoDtoResponse(frigoAliment.getFrigo().getId_frigo(), frigoAliment.getFrigo().getId_utilisateur(), null),
                        frigoAliment.getId_aliment(),
                        frigoAliment.getQuantity()
                ))
                .collect(Collectors.toList());
    }
}
