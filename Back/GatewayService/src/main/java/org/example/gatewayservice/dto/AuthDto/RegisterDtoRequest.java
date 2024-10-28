package org.example.gatewayservice.dto.AuthDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDtoRequest {
    private String nom;
    private String email;
    private String password;
    private int phone;
    private int role;
}