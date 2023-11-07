package br.com.fredericopellegrini.petmania.auth;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
