package com.example.kanban.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service // Indica que essa classe é um componente gerenciado pelo Spring
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Map<String, String> users = new HashMap<>();

    public UserDetailsServiceImpl() {
        // Simulação de usuário armazenado em memória.
        // Use um PasswordEncoder para criptografar senhas reais.
        users.put("usuario_exemplo", "$2a$10$EixZaYVK1fsbw1ZfbX3OXePaWxn96p36yquVjDZZ49axwo1R9dW2K"); // senha_exemplo
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Verifica se o usuário existe
        if (!users.containsKey(username)) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }

        // Retorna o usuário encontrado com suas credenciais
        return User.builder()
                .username(username)
                .password(users.get(username)) // Senha criptografada
                .roles("USER") // Adiciona a role do usuário
                .build();
    }
}
