package org.example.authenticationservice.service;

import org.example.authenticationservice.Dto.UtilisateurDtoReceive;
import org.example.authenticationservice.Dto.UtilisateurDtoSend;
import org.example.authenticationservice.entity.UserApp;
import org.example.authenticationservice.repository.UserAppRepository;
import org.example.authenticationservice.utils.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {

    @Autowired
    private UserAppRepository userAppRepository;

    public UtilisateurService(UserAppRepository userAppRepository) {
        this.userAppRepository = userAppRepository;
    }

    private UtilisateurDtoSend userAppMapperUtilisateurDTOSend(UserApp utilisateur) {
        return UtilisateurDtoSend.builder()
                .id_utilisateur(utilisateur.getId_user())
                .pseudo(utilisateur.getPseudo())
                .email(utilisateur.getEmail())
                .password(utilisateur.getPassword())
                .role(utilisateur.getRole())
                .build();
    }

    private List<UtilisateurDtoSend> listeUserAppMapperListeUtilisateurDTOSend(List<UserApp> utilisateurs) {
        return utilisateurs.stream().map(this::userAppMapperUtilisateurDTOSend).toList();
    }

    private UserApp findById(int id) {
        return userAppRepository.findById(id).orElse(null);
    }


    public List<UtilisateurDtoSend> getAll() {
        return listeUserAppMapperListeUtilisateurDTOSend((List<UserApp>) userAppRepository.findAll());
    }

    public UtilisateurDtoSend getById(int id) {
        return userAppMapperUtilisateurDTOSend(userAppRepository.findById(id).orElseThrow());
    }

    public UtilisateurDtoSend save(UtilisateurDtoReceive utilisateurDtoReceive) {

        UserApp utilisateur = UserApp.builder()
                .pseudo(utilisateurDtoReceive.getPseudo())
                .email(utilisateurDtoReceive.getEmail())
                .password(utilisateurDtoReceive.getPassword())
                .role(Role.USER)
                .build();

        return userAppMapperUtilisateurDTOSend(userAppRepository.save(utilisateur));
    }

    public UtilisateurDtoSend update(int id, UtilisateurDtoReceive utilisateurDtoReceive) {
        UserApp utilisateur = findById(id);

        utilisateur.setPseudo(utilisateurDtoReceive.getPseudo());
        utilisateur.setEmail(utilisateurDtoReceive.getEmail());
        utilisateur.setPassword(utilisateurDtoReceive.getPassword());
        utilisateur.setRole(Role.USER);

        return userAppMapperUtilisateurDTOSend(userAppRepository.save(utilisateur));
    }

    public void delete(int id) {
        userAppRepository.delete(findById(id));
    }


}

