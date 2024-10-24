package org.example.utilisateurservice.service;

import org.example.utilisateurservice.dto.UtilisateurDtoReceive;
import org.example.utilisateurservice.dto.UtilisateurDtoSend;
import org.example.utilisateurservice.entity.Utilisateur;
import org.example.utilisateurservice.repository.UtilisateurRepository;
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
                .build();

        return utilisateurMapperUtilisateurDTOSend(utilisateurRepository.save(utilisateur));
    }

    public UtilisateurDtoSend update(int id, UtilisateurDtoReceive utilisateurDtoReceive) {
        Utilisateur utilisateur = findById(id);

        utilisateur.setNom(utilisateurDtoReceive.getNom());
        utilisateur.setEmail(utilisateurDtoReceive.getEmail());

        return utilisateurMapperUtilisateurDTOSend(utilisateurRepository.save(utilisateur));
    }

    public void delete(int id) {
        utilisateurRepository.delete(findById(id));
    }


}
