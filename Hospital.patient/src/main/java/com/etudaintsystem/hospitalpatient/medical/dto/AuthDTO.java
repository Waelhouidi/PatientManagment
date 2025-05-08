package com.etudaintsystem.hospitalpatient.medical.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

public class AuthDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginRequest {
        @NotBlank(message = "Le nom d'utilisateur est obligatoire")
        private String username;

        @NotBlank(message = "Le mot de passe est obligatoire")
        private String password;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RegisterRequest {
        @NotBlank(message = "Le nom d'utilisateur est obligatoire")
        @Size(min = 3, max = 20, message = "Le nom d'utilisateur doit contenir entre 3 et 20 caractères")
        private String username;

        @NotBlank(message = "L'email est obligatoire")
        @Size(max = 50, message = "L'email ne doit pas dépasser 50 caractères")
        @Email(message = "L'email doit être valide")
        private String email;

        @NotBlank(message = "Le mot de passe est obligatoire")
        @Size(min = 6, max = 40, message = "Le mot de passe doit contenir entre 6 et 40 caractères")
        private String password;

        private Set<String> roles;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class JwtResponse {
        private String token;
        private String type = "Bearer";
        private Long id;
        private String username;
        private String email;
        private Set<String> roles;

        public JwtResponse(String token, Long id, String username, String email, Set<String> roles) {
            this.token = token;
            this.id = id;
            this.username = username;
            this.email = email;
            this.roles = roles;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MessageResponse {
        private String message;
    }
}
