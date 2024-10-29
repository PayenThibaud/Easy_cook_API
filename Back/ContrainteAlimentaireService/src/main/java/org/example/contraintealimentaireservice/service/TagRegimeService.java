package org.example.contraintealimentaireservice.service;

import org.example.contraintealimentaireservice.dto.PrefAlimUserDTOSend;
import org.example.contraintealimentaireservice.dto.TagRegimeDTOReceive;
import org.example.contraintealimentaireservice.dto.TagRegimeDTOSend;
import org.example.contraintealimentaireservice.entity.PrefAlimUser;
import org.example.contraintealimentaireservice.entity.TagRegime;
import org.example.contraintealimentaireservice.repository.PrefAlimUserRepository;
import org.example.contraintealimentaireservice.repository.TagRegimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagRegimeService {
    @Autowired
    private TagRegimeRepository tagRegimeRepository;
    @Autowired
    private PrefAlimUserRepository prefAlimUserRepository;

    public TagRegimeService(TagRegimeRepository tagRegimeRepository) {
        this.tagRegimeRepository = tagRegimeRepository;
    }

    //MAPPAGE
    private TagRegimeDTOSend mapToDTO(TagRegime tagRegime) {
        return TagRegimeDTOSend.builder()
                .id(tagRegime.getId())
                .recetteId(tagRegime.getRecetteId())
                .contrainteAlimentaireId(tagRegime.getContrainteAlimentaireId())
                .build();
    }

    private List<TagRegimeDTOSend> mapToDTOList(List<TagRegime> tagRegimeList) {
        return tagRegimeList.stream().map(this::mapToDTO).toList();
    }

    //CREATE
    public TagRegimeDTOSend createTagRegime(TagRegimeDTOReceive tagRegimeDTOReceive) {
        TagRegime tagRegime = TagRegime.builder()
                .recetteId(tagRegimeDTOReceive.getRecetteId())
                .contrainteAlimentaireId(tagRegimeDTOReceive.getContrainteAlimentaireId())
                .build();
        TagRegime savedTagRegime = tagRegimeRepository.save(tagRegime);
        return mapToDTO (savedTagRegime);
    }


    //READ ALL
    public List<TagRegimeDTOSend> geAll() {
        return mapToDTOList((List<TagRegime>) tagRegimeRepository.findAll());
    }


    //READ ID_contrainteAlimentaire

    public List<TagRegimeDTOSend> getByContrainteAlimentaireId(int id_contrainte) {
        return mapToDTOList(tagRegimeRepository.findByContrainteAlimentaireId(id_contrainte));
    }


    //DELETE par ID_recette
    @Transactional
    public void deleteAllByRecetteID(int recetteId){
        List<TagRegime> tagRegimeList = tagRegimeRepository.findAllByRecetteId(recetteId);

        for (TagRegime tagRegime : tagRegimeList) {
            tagRegimeRepository.delete(tagRegime);
        }

    }

}
