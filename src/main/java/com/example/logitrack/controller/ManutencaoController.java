package com.example.logitrack.controller;

import com.example.logitrack.model.Manutencao;
import com.example.logitrack.service.ManutencaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/manutencoes")
@RequiredArgsConstructor
public class ManutencaoController {

    private final ManutencaoService manutencaoService;

    @GetMapping
    public ResponseEntity<List<Manutencao>> listar() {
        return ResponseEntity.ok(manutencaoService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manutencao> buscarPorId(@PathVariable @NonNull Long id) {
        return ResponseEntity.ok(manutencaoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Manutencao> criar(@RequestBody @NonNull Manutencao manutencao) {
        return ResponseEntity.ok(manutencaoService.salvar(manutencao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manutencao> atualizar(@PathVariable @NonNull Long id, @RequestBody @NonNull Manutencao manutencao) {
        return ResponseEntity.ok(manutencaoService.atualizar(id, manutencao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable @NonNull Long id) {
        manutencaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/proximas")
    public ResponseEntity<List<Manutencao>> getProximas() {
        return ResponseEntity.ok(manutencaoService.proximasManutencoes());
    }
}