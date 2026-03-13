package com.example.logitrack.repository;

import com.example.logitrack.model.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ViagemRepository extends JpaRepository<Viagem, Long> {

    @Query("SELECT SUM(v.kmPercorrida) FROM Viagem v")
    Double totalKmFrota();

    @Query("SELECT SUM(v.kmPercorrida) FROM Viagem v WHERE v.veiculo.id = :veiculoId")
    Double totalKmPorVeiculo(@Param("veiculoId") Long veiculoId);

    @Query("SELECT v.veiculo.tipo, COUNT(v) FROM Viagem v GROUP BY v.veiculo.tipo")
    List<Object[]> volumePorCategoria();

    @Query("SELECT v.veiculo.modelo, SUM(v.kmPercorrida) as total FROM Viagem v GROUP BY v.veiculo.modelo ORDER BY total DESC")
    List<Object[]> rankingUtilizacao();
}