package br.com.rushball.rushball.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import br.com.rushball.rushball.entity.Selecao;
import br.com.rushball.rushball.repository.SelecaoRepository;

@Service
public class SelecaoService {

    private final SelecaoRepository selecaoRepository;

    public SelecaoService(SelecaoRepository selecaoRepository) {
        this.selecaoRepository = selecaoRepository;
    }

    public List<Selecao> listarTodas() {
        return selecaoRepository.findAll();
    }

    public Selecao buscarPorId(Long id) {
        return selecaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Seleção não encontrada: " + id));
    }

    public Selecao salvar(Selecao selecao) {
        return selecaoRepository.save(selecao);
    }

    public void excluir(Long id) {
        if (!selecaoRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Seleção não encontrada: " + id);
        }

        selecaoRepository.deleteById(id);
    }
}