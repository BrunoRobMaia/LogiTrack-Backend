package com.example.logitrack.service;

import com.example.logitrack.exception.ResourceNotFoundException;
import com.example.logitrack.model.Manutencao;
import com.example.logitrack.repository.ManutencaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManutencaoService {

    private final ManutencaoRepository manutencaoRepository;

    @Transactional(readOnly = true)
    public List<Manutencao> listarTodas() {
        return manutencaoRepository.findAll();
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("null")
    public @NonNull Manutencao buscarPorId(@NonNull Long id) {
        return manutencaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manutenção não encontrada: " + id));
    }

    @Transactional
    public @NonNull Manutencao salvar(@NonNull Manutencao manutencao) {
        return manutencaoRepository.save(manutencao);
    }

    @Transactional
    public @NonNull Manutencao atualizar(@NonNull Long id, @NonNull Manutencao manutencao) {
        buscarPorId(id);
        manutencao.setId(id);
        return manutencaoRepository.save(manutencao);
    }

    @Transactional
    public void deletar(@NonNull Long id) {
        buscarPorId(id);
        manutencaoRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public List<Manutencao> proximasManutencoes() {
        return manutencaoRepository.proximasManutencoes();
    }
}
