package org.example.authenticationservice.controller;

import org.example.authenticationservice.Dto.*;
import org.example.authenticationservice.entity.UserApp;
import org.example.authenticationservice.exception.UserAlreadyExistException;
import org.example.authenticationservice.security.JWTGenerator;
import org.example.authenticationservice.exception.NotFoundException;
import org.example.authenticationservice.service.UserAppService;
import org.example.authenticationservice.utils.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UserAppController {

    private final AuthenticationManager authenticationManager;
    private final UserAppService userAppService;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator generator;

    @Autowired
    public UserAppController(AuthenticationManager authenticationManager, UserAppService userAppService, PasswordEncoder passwordEncoder, JWTGenerator generator) {
        this.authenticationManager = authenticationManager;
        this.userAppService = userAppService;
        this.passwordEncoder = passwordEncoder;
        this.generator = generator;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = generator.generateToken(authentication);
        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto registerRequestDTO) throws UserAlreadyExistException {
        registerRequestDTO.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        UserApp userApp = userAppService.enregistrerUtilisateur(registerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new RegisterResponseDto(userApp.getId_user(), userApp.getPseudo(), userApp.getEmail(), userApp.getPassword(), userApp.getRole().ordinal())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDtoSend> getById(@PathVariable int id) {
        return ResponseEntity.ok(userAppService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<UtilisateurDtoSend>> getAll() {
        return ResponseEntity.ok(userAppService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurDtoSend> update(@PathVariable int id, @RequestBody UtilisateurDtoReceive utilisateurDtoReceive) {
        return ResponseEntity.ok(userAppService.update(id, utilisateurDtoReceive));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        userAppService.delete(id);
        return ResponseEntity.ok("{\"message\": \"Utilisateur supprimé avec succès\"}");
    }

}