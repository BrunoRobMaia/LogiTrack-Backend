package com.example.logitrack.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ManutencaoDTO(
    Long id,
    Long veiculoId,
    String veiculoModelo,
    String veiculoPlaca,
    String veiculoTipo,
    LocalDate dataInicio,
    LocalDate dataFinalizacao,
    String tipoServico,
    BigDecimal custoEstimado,
    String status
) {}