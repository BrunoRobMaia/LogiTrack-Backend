package com.example.logitrack.security;

import com.example.logitrack.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtFilter.class);

    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String token = null;

        // Tenta pegar do header Authorization
        String header = request.getHeader("Authorization");
        log.info("Authorization header: {}", header != null ? "presente" : "ausente");

        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
            log.info("Token encontrado no header");
        }

        // Se não encontrou no header, tenta pegar do cookie
        if (token == null && request.getCookies() != null) {
            token = Arrays.stream(request.getCookies())
                    .filter(c -> "token".equals(c.getName()))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);
            log.info("Token encontrado no cookie: {}", token != null);
        }

        if (token != null) {
            boolean valido = jwtUtil.validarToken(token);
            log.info("Token válido: {}", valido);

            if (valido) {
                String email = jwtUtil.extrairEmail(token);
                log.info("Email extraído: {}", email);

                usuarioRepository.findByEmail(email).ifPresent(usuario -> {
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(email, null, List.of());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    log.info("Autenticação configurada para: {}", email);
                });
            }
        } else {
            log.warn("Nenhum token encontrado na requisição para: {}", request.getRequestURI());
        }

        filterChain.doFilter(request, response);
    }
}