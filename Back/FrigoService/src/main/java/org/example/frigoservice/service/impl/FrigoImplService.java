package org.example.frigoservice.service.impl;

import org.example.frigoservice.dto.FrigoAlimentDto.FrigoAlimentDtoResponse;
import org.example.frigoservice.dto.FrigoDto.FrigoDtoRequest;
import org.example.frigoservice.dto.FrigoDto.FrigoDtoResponse;
import org.example.frigoservice.entity.Frigo;
import org.example.frigoservice.repository.FrigoRepository;
import org.example.frigoservice.service.FrigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FrigoImplService implements FrigoService {

    @Autowired
    private FrigoRepository frigoRepository;

    @Override
    public FrigoDtoResponse createFrigo(FrigoDtoRequest frigoDtoRequest) {
        Frigo frigo = Frigo.builder()
                .id_utilisateur(frigoDtoRequest.getIdUtilisateur())
                .build();
        Frigo savedFrigo = frigoRepository.save(frigo);

        return new FrigoDtoResponse(
                savedFrigo.getId_frigo(),
                savedFrigo.getId_utilisateur(),
                List.of()
        );
    }

    @Override
    public boolean deleteFrigo(int id) {
        if (frigoRepository.existsById(id)) {
            frigoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public FrigoDtoResponse getFrigoById(int id) {
        Optional<Frigo> frigoOpt = frigoRepository.findById(id);
        if (frigoOpt.isPresent()) {
            Frigo frigo = frigoOpt.get();
            return new FrigoDtoResponse(
                    frigo.getId_frigo(),
                    frigo.getId_utilisateur(),
                    frigo.getAlimentsDansLeFrigo().stream()
                            .map(aliment -> new FrigoAlimentDtoResponse(
                                    aliment.getId_frigoAliment(),
                                    null,
                                    aliment.getId_aliment(),
                                    aliment.getQuantity()
                            ))
                            .collect(Collectors.toList())
            );
        }
        return null;
    }

    @Override
    public List<FrigoDtoResponse> getAllFrigo() {
        return frigoRepository.findAll().stream()
                .map(frigo -> new FrigoDtoResponse(
                        frigo.getId_frigo(),
                        frigo.getId_utilisateur(),
                        frigo.getAlimentsDansLeFrigo().stream()
                                .map(aliment -> new FrigoAlimentDtoResponse(
                                        aliment.getId(),
                                        null,
                                        aliment.getId_aliment(),
                                        aliment.getQuantity()
                                ))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Frigo getFrigoByUtilisateurId(int id) {
        return frigoRepository.findByUtilisateurId(id)
                .orElseThrow(() -> new IllegalArgumentException("No fridge found for user ID " + id));
    }
}
