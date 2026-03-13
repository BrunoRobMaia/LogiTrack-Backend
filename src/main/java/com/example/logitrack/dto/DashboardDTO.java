package com.example.logitrack.dto;

import java.util.List;

public record DashboardDTO(
    Double totalKmFrota,
    List<CategoriaDTO> volumePorCategoria,
    List<RankingDTO> rankingUtilizacao
) {
    public record CategoriaDTO(String tipo, Long quantidade) {}
    public record RankingDTO(String modelo, Double km) {}
}