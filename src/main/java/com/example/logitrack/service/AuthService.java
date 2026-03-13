package com.example.logitrack.service;

import com.example.logitrack.dto.AuthDTO;
import com.example.logitrack.model.Usuario;
import com.example.logitrack.repository.UsuarioRepository;
import com.example.logitrack.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public String register(AuthDTO.RegisterRequest request) {
        if (usuarioRepository.findByEmail(request.email()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado!");
        }
        Usuario usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(passwordEncoder.encode(request.senha()));
        usuarioRepository.save(usuario);
        return "Usuário cadastrado com sucesso!";
    }

    public AuthDTO.TokenResponse login(AuthDTO.LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.email())
                .filter(u -> passwordEncoder.matches(request.senha(), u.getSenha()))
                .orElseThrow(() -> new IllegalArgumentException("Email ou senha inválidos!"));
        return new AuthDTO.TokenResponse(jwtUtil.gerarToken(usuario.getEmail()));
    }
}