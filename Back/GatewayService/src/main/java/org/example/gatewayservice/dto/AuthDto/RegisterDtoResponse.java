package org.example.gatewayservice.dto.AuthDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDtoResponse {
    private int id_utilisateurApp;
    private String nom;
    private String email;
    private int phone;
    private int role;
}