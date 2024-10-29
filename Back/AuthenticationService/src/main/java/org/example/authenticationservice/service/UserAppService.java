package org.example.authenticationservice.service;

import org.example.authenticationservice.Dto.RegisterRequestDto;
import org.example.authenticationservice.Dto.UtilisateurDtoReceive;
import org.example.authenticationservice.Dto.UtilisateurDtoSend;
import org.example.authenticationservice.entity.UserApp;
import org.example.authenticationservice.exception.UserAlreadyExistException;
import org.example.authenticationservice.repository.UserAppRepository;
import org.example.authenticationservice.utils.enums.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAppService {

    private final UserAppRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserAppService(UserAppRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

    public List<UtilisateurDtoSend> getAll() {
        return listeUserAppMapperListeUtilisateurDTOSend((List<UserApp>) userRepository.findAll());
    }

    public UtilisateurDtoSend getById(int id) {
        return userRepository.findById(id)
                .map(this::userAppMapperUtilisateurDTOSend)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }



    public UtilisateurDtoSend update(int id, UtilisateurDtoReceive utilisateurDtoReceive) {
        UserApp utilisateur = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        utilisateur.setPseudo(utilisateurDtoReceive.getPseudo());
        utilisateur.setEmail(utilisateurDtoReceive.getEmail());

        if (!utilisateurDtoReceive.getPassword().isEmpty() &&
                !passwordEncoder.matches(utilisateurDtoReceive.getPassword(), utilisateur.getPassword())) {
            utilisateur.setPassword(passwordEncoder.encode(utilisateurDtoReceive.getPassword()));
        }

        return userAppMapperUtilisateurDTOSend(userRepository.save(utilisateur));
    }

    public void delete(int id) {
        UserApp utilisateur = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(utilisateur);
    }

    public UserApp enregistrerUtilisateur(RegisterRequestDto registerRequestDto) throws UserAlreadyExistException {
        Optional<UserApp> userAppOptional = userRepository.findByEmail(registerRequestDto.getEmail());
        if (userAppOptional.isPresent()) {
            throw new UserAlreadyExistException();
        }

        UserApp user = new UserApp(registerRequestDto.getPseudo(), registerRequestDto.getEmail(), registerRequestDto.getPassword(), registerRequestDto.getRole());
        return userRepository.save(user);
    }
}