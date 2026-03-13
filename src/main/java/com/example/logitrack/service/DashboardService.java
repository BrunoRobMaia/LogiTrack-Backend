package com.example.logitrack.service;

import com.example.logitrack.dto.DashboardDTO;
import com.example.logitrack.model.Manutencao;
import com.example.logitrack.repository.ManutencaoRepository;
import com.example.logitrack.repository.ViagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final ViagemRepository viagemRepository;
    private final ManutencaoRepository manutencaoRepository;

    @Transactional(readOnly = true)
    public DashboardDTO getDashboard() {
        List<DashboardDTO.CategoriaDTO> categorias = viagemRepository.volumePorCategoria()
                .stream()
                .map(row -> new DashboardDTO.CategoriaDTO(
                        (String) row[0],
                        ((Number) row[1]).longValue()))
                .toList();

        List<DashboardDTO.RankingDTO> ranking = viagemRepository.rankingUtilizacao()
                .stream()
                .map(row -> new DashboardDTO.RankingDTO(
                        (String) row[0],
                        ((Number) row[1]).doubleValue()))
                .toList();

        return new DashboardDTO(
                viagemRepository.totalKmFrota(),
                categorias,
                ranking
        );
    }

    @Transactional(readOnly = true)
    public List<Manutencao> getProximasManutencoes() {
        return manutencaoRepository.proximasManutencoes();
    }

    @Transactional(readOnly = true)
    public Double getProjecaoFinanceira() {
        return manutencaoRepository.custoTotalMesAtual();
    }
}