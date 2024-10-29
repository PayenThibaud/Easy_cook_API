package org.example.authenticationservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.authenticationservice.utils.enums.Role;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_user;

    @Column(unique = true)
    private String pseudo;
    private String email;
    private String password;
    private Role role;

    public UserApp(String pseudo, String email, String password,int role) {
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
        this.role = role == 0 ? Role.USER : Role.ADMIN;
    }
}
