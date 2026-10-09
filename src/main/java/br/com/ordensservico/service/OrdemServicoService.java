package br.com.ordensservico.service;

import br.com.ordensservico.dto.OrdemServicoRequest;
import br.com.ordensservico.model.Equipamento;
import br.com.ordensservico.model.OrdemServico;
import br.com.ordensservico.repository.EquipamentoRepository;
import br.com.ordensservico.repository.OrdemServicoRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OrdemServicoService {
    
    private final OrdemServicoRepository repository;
    private final EquipamentoRepository equipamentoRepository;

    public OrdemServicoService (OrdemServicoRepository repository, EquipamentoRepository equipamentoRepository) {
        this.repository = repository;
        this.equipamentoRepository = equipamentoRepository;
    }

    public Optional<OrdemServico> cadastrar(OrdemServicoRequest request) {
        if (request.getEquipamentoId() == null) {
            return Optional.empty();
        }

        Optional<Equipamento> equipamentoEncontrado = equipamentoRepository.findById(request.getEquipamentoId());
        if (equipamentoEncontrado.isEmpty()) {
            return Optional.empty();
        }

        OrdemServico ordemServico = new OrdemServico();
        ordemServico.setDescricao(request.getDescricao());
        ordemServico.setEquipamento(equipamentoEncontrado.get());
        ordemServico.setDataAbertura(LocalDateTime.now());

        return Optional.of(repository.save(ordemServico));

    }

    public List<OrdemServico> listar() {
        return repository.findAll();
    }

    public Optional<OrdemServico> buscarPorId(Integer id) {
        return repository.findById(id);
    }

}
