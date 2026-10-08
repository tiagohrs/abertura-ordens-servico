package br.com.ordensservico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ordensservico.dto.EquipamentoRequest;
import br.com.ordensservico.model.Equipamento;
import br.com.ordensservico.model.Setor;
import br.com.ordensservico.repository.EquipamentoRepository;
import br.com.ordensservico.repository.SetorRepository;

@Service
public class EquipamentoService {

    private final EquipamentoRepository repository;
    private final SetorRepository setorRepository;

    public EquipamentoService(EquipamentoRepository repository, SetorRepository setorRepository) {
        this.repository = repository;
        this.setorRepository = setorRepository;
    }

    public Optional<Equipamento> cadastrar(EquipamentoRequest request) {
        if (request.getSetorId() == null) {
            return Optional.empty();
        }

        Optional<Setor> setorEncontrado = setorRepository.findById(request.getSetorId());
        if (setorEncontrado.isEmpty()) {
            return Optional.empty();
        }

        Equipamento equipamento = new Equipamento();
        equipamento.setNome(request.getNome());
        equipamento.setNumeroPatrimonio(request.getNumeroPatrimonio());
        equipamento.setSetor(setorEncontrado.get());

        return Optional.of(repository.save(equipamento));
    }

    public List<Equipamento> listar() {
        return repository.findAll();
    }

    public Optional<Equipamento> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Optional<Equipamento> atualizar(Integer id, EquipamentoRequest request) {
        Optional<Equipamento> existente = repository.findById(id);
        if (existente.isEmpty() || request.getSetorId() == null) {
            return Optional.empty();
        }

        Optional<Setor> setorEncontrado = setorRepository.findById(request.getSetorId());
        if (setorEncontrado.isEmpty()) {
            return Optional.empty();
        }

        Equipamento equipamento = existente.get();
        equipamento.setNome(request.getNome());
        equipamento.setNumeroPatrimonio(request.getNumeroPatrimonio());
        equipamento.setSetor(setorEncontrado.get());

        return Optional.of(repository.save(equipamento));
    }

    public boolean excluir(Integer id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}