package br.com.ordensservico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    private Setor buscarSetor(Equipamento equipamento) {
        if (equipamento.getSetor() == null || equipamento.getSetor().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Setor não informado");
        }
        return setorRepository.findById(equipamento.getSetor().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Setor não encontrado"));
    }

    public Equipamento cadastrar(Equipamento equipamento) {
        Setor setor = buscarSetor(equipamento);
        equipamento.setId(null);
        equipamento.setSetor(setor);
        return repository.save(equipamento);
    }

    public List<Equipamento> listar() {
        return repository.findAll();
    }

    public Optional<Equipamento> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Optional<Equipamento> atualizar(Integer id, Equipamento dados) {
        Optional<Equipamento> existente = repository.findById(id);
        if (existente.isEmpty()) {
            return Optional.empty();
        }
        Setor setor = buscarSetor(dados);
        Equipamento equipamento = existente.get();
        equipamento.setNome(dados.getNome());
        equipamento.setNumeroPatrimonio(dados.getNumeroPatrimonio());
        equipamento.setSetor(setor);
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