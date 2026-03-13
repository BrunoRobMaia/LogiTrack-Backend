package com.example.logitrack.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ViagemDTO(
    Long id,
    Long veiculoId,
    String veiculoModelo,
    String veiculoPlaca,
    LocalDateTime dataSaida,
    LocalDateTime dataChegada,
    String origem,
    String destino,
    BigDecimal kmPercorrida
) {}