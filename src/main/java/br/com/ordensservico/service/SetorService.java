package br.com.ordensservico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ordensservico.model.Setor;
import br.com.ordensservico.repository.SetorRepository;

@Service
public class SetorService {

    private final SetorRepository repository;

    public SetorService(SetorRepository repository) {
        this.repository = repository;
    }

    public Setor cadastrar(Setor setor) {
        setor.setId(null);
        return repository.save(setor);
    }

    public List<Setor> listar() {
        return repository.findAll();
    }

    public Optional<Setor> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Optional<Setor> atualizar(Integer id, Setor dados) {
        Optional<Setor> existente = repository.findById(id);
        if (existente.isEmpty()) {
            return Optional.empty();
        }
        Setor setor = existente.get();
        setor.setNome(dados.getNome());
        return Optional.of(repository.save(setor));
    }

    public boolean excluir(Integer id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}