package com.example.logitrack.dto;

public record AuthDTO() {
    public record LoginRequest(String email, String senha) {}
    public record RegisterRequest(String nome, String email, String senha) {}
    public record TokenResponse(String token) {}
}