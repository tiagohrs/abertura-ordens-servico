package br.com.ordensservico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ordensservico.model.Equipamento;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Integer> {
}