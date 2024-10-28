package org.example.authenticationservice.service;

import org.example.authenticationservice.Dto.RegisterRequestDto;
import org.example.authenticationservice.entity.UtilisateurApp;
import org.example.authenticationservice.exception.UserAlreadyExistException;
import org.example.authenticationservice.repository.UtilisateurAppRepository;
import org.example.authenticationservice.utils.enums.Role;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurAppService {
    private final UtilisateurAppRepository utilisateurAppRepository;

    public UtilisateurAppService(UtilisateurAppRepository utilisateurAppRepository) {
        this.utilisateurAppRepository = utilisateurAppRepository;
    }


    public UtilisateurApp enregistrerUtilisateur(RegisterRequestDto registerRequestDto) throws UserAlreadyExistException {
        Optional<UtilisateurApp> utilisateurAppOptional = utilisateurAppRepository.findByEmail(registerRequestDto.getEmail());
        if(utilisateurAppOptional.isEmpty()){

            UtilisateurApp utilisateurApp = UtilisateurApp.builder()
                    .nom(registerRequestDto.getNom())
                    .email(registerRequestDto.getEmail())
                    .password(registerRequestDto.getPassword())
                    .phone(registerRequestDto.getPhone())
                    .role(Role.USER)
                    .build();

            return utilisateurAppRepository.save(utilisateurApp);
        }
        throw new UserAlreadyExistException();
    }


}
