package com.example.logitrack.service;

import com.example.logitrack.exception.ResourceNotFoundException;
import com.example.logitrack.model.Viagem;
import com.example.logitrack.repository.ViagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ViagemService {

    private final ViagemRepository viagemRepository;

    @Transactional(readOnly = true)
    public List<Viagem> listarTodas() {
        return viagemRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Viagem buscarPorId(@NonNull Long id) {
        return viagemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Viagem não encontrada: " + id));
    }

    @Transactional
    public Viagem salvar(@NonNull Viagem viagem) {
        return viagemRepository.save(viagem);
    }

    @Transactional
    public Viagem atualizar(@NonNull Long id, @NonNull Viagem viagem) {
        buscarPorId(id);
        viagem.setId(id);
        return viagemRepository.save(viagem);
    }

    @Transactional
    public void deletar(@NonNull Long id) {
        buscarPorId(id);
        viagemRepository.deleteById(id);
    }
}