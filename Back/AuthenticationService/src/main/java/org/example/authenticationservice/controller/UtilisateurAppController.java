package org.example.authenticationservice.controller;

import org.example.authenticationservice.Dto.LoginRequestDto;
import org.example.authenticationservice.Dto.LoginResponseDto;
import org.example.authenticationservice.Dto.RegisterRequestDto;
import org.example.authenticationservice.Dto.RegisterResponseDto;
import org.example.authenticationservice.entity.UtilisateurApp;
import org.example.authenticationservice.exception.UserAlreadyExistException;
import org.example.authenticationservice.security.JWTGenerator;
import org.example.authenticationservice.exception.NotFoundException;
import org.example.authenticationservice.service.UtilisateurAppService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class UtilisateurAppController {

    private final AuthenticationManager authenticationManager;
    private final UtilisateurAppService utilisateurAppService;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator generator;

    public UtilisateurAppController(AuthenticationManager authenticationManager, UtilisateurAppService utilisateurAppService,PasswordEncoder passwordEncoder,JWTGenerator generator) {
        this.authenticationManager = authenticationManager;
        this.utilisateurAppService = utilisateurAppService;
        this.passwordEncoder = passwordEncoder;
        this.generator = generator;

    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDTO) throws NotFoundException {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok(LoginResponseDto.builder().token(generator.generateToken(authentication)).build());
        }catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @PostMapping("register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto registerRequestDTO) throws UserAlreadyExistException {
        registerRequestDTO.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        UtilisateurApp utilisateurApp = utilisateurAppService.enregistrerUtilisateur(registerRequestDTO);
        return ResponseEntity.ok(RegisterResponseDto.builder()
                .id_utilisateurApp(utilisateurApp.getId_utilisateurApp())
                .nom(utilisateurApp.getNom())
                .email(utilisateurApp.getEmail())
                .phone(utilisateurApp.getPhone())
                .role(utilisateurApp.getRole().ordinal())
                .build());
    }
}