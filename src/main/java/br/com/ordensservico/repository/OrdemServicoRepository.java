package br.com.ordensservico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ordensservico.model.OrdemServico;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Integer> {
}
