package org.example.authenticationservice.service;

import org.example.authenticationservice.Dto.RegisterRequestDto;
import org.example.authenticationservice.entity.UserApp;
import org.example.authenticationservice.exception.UserAlreadyExistException;
import org.example.authenticationservice.repository.UserAppRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAppService {

    private final UserAppRepository userRepository;

    public UserAppService(UserAppRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserApp enregistrerUtilisateur(RegisterRequestDto registerRequestDto) throws UserAlreadyExistException {
        Optional<UserApp> userAppOptional = userRepository.findByEmail(registerRequestDto.getEmail());
        if(userAppOptional.isEmpty()){
            UserApp user = new UserApp(registerRequestDto.getPseudo(), registerRequestDto.getEmail(), registerRequestDto.getPassword(), registerRequestDto.getRole());
            return userRepository.save(user);
        }
        throw new UserAlreadyExistException();
    }


}
