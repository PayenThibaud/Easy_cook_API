package org.example.authenticationservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.authenticationservice.utils.enums.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDto {
    private int id_utilisateurApp;
    private String nom;
    private String email;
    private int phone;
    private int role;
}
