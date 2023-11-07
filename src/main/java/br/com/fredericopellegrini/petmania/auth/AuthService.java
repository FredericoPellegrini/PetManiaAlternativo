package br.com.fredericopellegrini.petmania.auth;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final String ADMIN_USERNAME = "admin";
    private final String ADMIN_PASSWORD = "admin";

    public boolean authenticate(String username, String password) {
        return username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD);
    }
}
