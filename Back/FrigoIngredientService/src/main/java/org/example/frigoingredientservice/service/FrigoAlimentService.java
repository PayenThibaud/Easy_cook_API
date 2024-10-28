package org.example.frigoingredientservice.service;

import org.example.frigoingredientservice.dto.FrigoAlimentDtoReceive;
import org.example.frigoingredientservice.dto.FrigoAlimentDtoSend;
import org.example.frigoingredientservice.entity.FrigoAliment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.frigoingredientservice.repository.FrigoAlimentRepository;

import java.util.List;

@Service
public class FrigoAlimentService {

    @Autowired
    private FrigoAlimentRepository frigoAlimentRepository;

    public FrigoAlimentService(FrigoAlimentRepository frigoAlimentRepository) {
        this.frigoAlimentRepository = frigoAlimentRepository;
    }

    private FrigoAlimentDtoSend frigoAlimentMapperFrigoAlimentDTOSend(FrigoAliment frigoAliment) {
        return FrigoAlimentDtoSend.builder()
                .id_frigoAliment(frigoAliment.getId_frigoAliment())
                .id_frigo(frigoAliment.getId_frigo())
                .id_aliment(frigoAliment.getId_aliment())
                .nombreAliment(frigoAliment.getNombreAliment())
                .build();
    }

    private List<FrigoAlimentDtoSend> listeFrigoAlimentMapperListeFrigoAlimentDTOSend(List<FrigoAliment> frigoAliments) {
        return frigoAliments.stream().map(this::frigoAlimentMapperFrigoAlimentDTOSend).toList();
    }

    private FrigoAliment findById(int id) {
        return frigoAlimentRepository.findById(id).orElse(null);
    }


    public List<FrigoAlimentDtoSend> getAll() {
        return listeFrigoAlimentMapperListeFrigoAlimentDTOSend((List<FrigoAliment>) frigoAlimentRepository.findAll());
    }

    public FrigoAlimentDtoSend getById(int id) {
        return frigoAlimentMapperFrigoAlimentDTOSend(frigoAlimentRepository.findById(id).orElseThrow());
    }

    public FrigoAlimentDtoSend save(FrigoAlimentDtoReceive frigoAlimentDtoReceive) {

        FrigoAliment frigoAliment = FrigoAliment.builder()
                .id_frigo(frigoAlimentDtoReceive.getId_frigo())
                .id_aliment(frigoAlimentDtoReceive.getId_aliment())
                .nombreAliment(frigoAlimentDtoReceive.getNombreAliment())
                .build();

        return frigoAlimentMapperFrigoAlimentDTOSend(frigoAlimentRepository.save(frigoAliment));
    }

    public FrigoAlimentDtoSend update(int id, FrigoAlimentDtoReceive frigoAlimentDtoReceive) {
        FrigoAliment frigoAliment = findById(id);

        frigoAliment.setId_aliment(frigoAlimentDtoReceive.getId_aliment());
        frigoAliment.setNombreAliment(frigoAlimentDtoReceive.getNombreAliment());
        frigoAliment.setId_frigo(frigoAlimentDtoReceive.getId_frigo());

        return frigoAlimentMapperFrigoAlimentDTOSend(frigoAlimentRepository.save(frigoAliment));
    }

    public void delete(int id) {
        frigoAlimentRepository.delete(findById(id));
    }


}
