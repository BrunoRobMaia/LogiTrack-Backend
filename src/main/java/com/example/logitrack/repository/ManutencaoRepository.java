package com.example.logitrack.repository;

import com.example.logitrack.model.Manutencao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {

    @Query(value = "SELECT * FROM manutencoes WHERE status != 'CONCLUIDA' ORDER BY data_inicio ASC LIMIT 5", nativeQuery = true)
    List<Manutencao> proximasManutencoes();

    @Query(value = "SELECT COALESCE(SUM(custo_estimado), 0) FROM manutencoes WHERE status != 'CONCLUIDA'", nativeQuery = true)
    Double custoTotalMesAtual();
}