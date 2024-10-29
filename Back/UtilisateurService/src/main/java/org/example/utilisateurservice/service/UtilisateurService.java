package org.example.utilisateurservice.service;

import org.example.utilisateurservice.dto.UtilisateurDtoReceive;
import org.example.utilisateurservice.dto.UtilisateurDtoSend;
import org.example.utilisateurservice.entity.Utilisateur;
import org.example.utilisateurservice.repository.UtilisateurRepository;
import org.example.utilisateurservice.utils.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    private UtilisateurDtoSend utilisateurMapperUtilisateurDTOSend(Utilisateur utilisateur) {
        return UtilisateurDtoSend.builder()
                .id_utilisateur(utilisateur.getId_utilisateur())
                .nom(utilisateur.getNom())
                .email(utilisateur.getEmail())
                .password(utilisateur.getPassword())
                .phone(utilisateur.getPhone())
                .role(Role.USER)
                .build();
    }

    private List<UtilisateurDtoSend> listeUtilisateurMapperListeUtilisateurDTOSend(List<Utilisateur> utilisateurs) {
        return utilisateurs.stream().map(this::utilisateurMapperUtilisateurDTOSend).toList();
    }

    private Utilisateur findById(int id) {
        return utilisateurRepository.findById(id).orElse(null);
    }


    public List<UtilisateurDtoSend> getAll() {
        return listeUtilisateurMapperListeUtilisateurDTOSend((List<Utilisateur>) utilisateurRepository.findAll());
    }

    public UtilisateurDtoSend getById(int id) {
        return utilisateurMapperUtilisateurDTOSend(utilisateurRepository.findById(id).orElseThrow());
    }

    public UtilisateurDtoSend save(UtilisateurDtoReceive utilisateurDtoReceive) {

        Utilisateur utilisateur = Utilisateur.builder()
                .nom(utilisateurDtoReceive.getNom())
                .email(utilisateurDtoReceive.getEmail())
                .password(utilisateurDtoReceive.getPassword())
                .phone(utilisateurDtoReceive.getPhone())
                .role(Role.USER)
                .build();

        return utilisateurMapperUtilisateurDTOSend(utilisateurRepository.save(utilisateur));
    }

    public UtilisateurDtoSend update(int id, UtilisateurDtoReceive utilisateurDtoReceive) {
        Utilisateur utilisateur = findById(id);

        utilisateur.setNom(utilisateurDtoReceive.getNom());
        utilisateur.setEmail(utilisateurDtoReceive.getEmail());
        utilisateur.setPhone(utilisateurDtoReceive.getPhone());
        utilisateur.setPassword(utilisateurDtoReceive.getPassword());
        utilisateur.setRole(Role.USER);

        return utilisateurMapperUtilisateurDTOSend(utilisateurRepository.save(utilisateur));
    }

    public void delete(int id) {
        utilisateurRepository.delete(findById(id));
    }


}
