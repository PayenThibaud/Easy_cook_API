package org.example.contraintealimentaireservice.service;

import org.example.contraintealimentaireservice.dto.ContrainteDTOSend;
import org.example.contraintealimentaireservice.dto.PrefAlimUserDTOReceive;
import org.example.contraintealimentaireservice.dto.PrefAlimUserDTOSend;
import org.example.contraintealimentaireservice.entity.ContrainteAlimentaire;
import org.example.contraintealimentaireservice.entity.PrefAlimUser;
import org.example.contraintealimentaireservice.repository.PrefAlimUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrefAlimUserService {

    @Autowired
    private PrefAlimUserRepository prefAlimUserRepository;
    @Autowired
    private ContrainteService contrainteService;

    public PrefAlimUserService(PrefAlimUserRepository prefAlimUserRepository) {
        this.prefAlimUserRepository = prefAlimUserRepository;
    }

    private PrefAlimUserDTOSend mapToDTO(PrefAlimUser prefAlimUser) {
        return PrefAlimUserDTOSend.builder()
                .id(prefAlimUser.getId())
                .utilisateurId(prefAlimUser.getUtilisateurId())
                .contrainteAlimentaireId(prefAlimUser.getContrainteAlimentaireId())
                .build();
    }

    private ContrainteAlimentaire mapDtoToEntity(ContrainteDTOSend dto) {
        return ContrainteAlimentaire.builder()
                .id_ContraiteAlimentaire(dto.getId_ContraiteAlimentaire())
                .nom(dto.getNom())
                .build();
    }
    private PrefAlimUser mapToEntity(PrefAlimUserDTOReceive prefAlimUserDTOReceive) {
        return PrefAlimUser.builder()
                .utilisateurId(prefAlimUserDTOReceive.getUtilisateurId())
                .contrainteAlimentaireId(prefAlimUserDTOReceive.getContrainteAlimentaireId())
                .build();
    }

    private List<PrefAlimUserDTOSend> mapToDTOList(List<PrefAlimUser> prefAlimUsers) {
        return prefAlimUsers.stream().map(this::mapToDTO).toList();
    }

    public PrefAlimUserDTOSend getById(int id) {
        return mapToDTO(prefAlimUserRepository.findById(id).orElseThrow());
    }

    public List<PrefAlimUserDTOSend> getAll() {
        return mapToDTOList((List<PrefAlimUser>) prefAlimUserRepository.findAll());
    }

    public PrefAlimUserDTOSend createPrefAlimUser(PrefAlimUserDTOReceive prefAlimUserDTOReceive) {
        PrefAlimUser newPrefAlimUser = mapToEntity(prefAlimUserDTOReceive);
        PrefAlimUser savedPrefAlimUser = prefAlimUserRepository.save(newPrefAlimUser);
        return mapToDTO(savedPrefAlimUser);
    }

    @Transactional
    public void deleteAllByUserId(int userId) {
        List<PrefAlimUser> prefAlimUsers = prefAlimUserRepository.findAllByUtilisateurId(userId);

        for (PrefAlimUser prefAlimUser : prefAlimUsers) {
            prefAlimUserRepository.delete(prefAlimUser);
        }
    }
}
