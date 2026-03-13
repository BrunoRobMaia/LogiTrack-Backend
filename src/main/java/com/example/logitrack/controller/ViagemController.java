package com.example.logitrack.controller;

import com.example.logitrack.model.Viagem;
import com.example.logitrack.service.ViagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/viagens")
@RequiredArgsConstructor
public class ViagemController {

    private final ViagemService viagemService;

    @GetMapping
    public ResponseEntity<List<Viagem>> listar() {
        return ResponseEntity.ok(viagemService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viagem> buscar(@PathVariable @NonNull Long id) {
        return ResponseEntity.ok(viagemService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Viagem> criar(@RequestBody @NonNull Viagem viagem) {
        return ResponseEntity.ok(viagemService.salvar(viagem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Viagem> atualizar(@PathVariable @NonNull Long id, @RequestBody @NonNull Viagem viagem) {
        return ResponseEntity.ok(viagemService.atualizar(id, viagem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable @NonNull Long id) {
        viagemService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}