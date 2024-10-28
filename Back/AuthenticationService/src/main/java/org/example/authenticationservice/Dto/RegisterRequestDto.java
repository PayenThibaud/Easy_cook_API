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
public class RegisterRequestDto {
    private String nom;
    private String email;
    private String password;
    private int phone;
    private int role;
}
