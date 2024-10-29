package org.example.contraintealimentaireservice.service;

import org.example.contraintealimentaireservice.dto.ContrainteDTOSend;
import org.example.contraintealimentaireservice.entity.ContrainteAlimentaire;
import org.example.contraintealimentaireservice.repository.ContrainteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContrainteService {

    @Autowired
    private ContrainteRepository contrainteRepository;

    public ContrainteService (ContrainteRepository contrainteRepository) {
        this.contrainteRepository = contrainteRepository;
    }

    private ContrainteDTOSend contrainteMapperContrainteDTOSend(ContrainteAlimentaire contrainte) {
        return ContrainteDTOSend.builder().
                id_ContraiteAlimentaire(contrainte.getId_ContraiteAlimentaire())
                .nom(contrainte.getNom())
                .build();
    }

    private List<ContrainteDTOSend> listeContrainteMapperListeDTOSend(List<ContrainteAlimentaire> listeContrainte) {
        return listeContrainte.stream().map(this::contrainteMapperContrainteDTOSend).toList();
    }

//    get by id
    public ContrainteDTOSend getById (int id) {
        return contrainteMapperContrainteDTOSend(contrainteRepository.findById(id).orElseThrow());
    }
//    get All
    public List<ContrainteDTOSend> getAll() {
        return listeContrainteMapperListeDTOSend((List<ContrainteAlimentaire>)contrainteRepository.findAll());
    }


}
