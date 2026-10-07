package br.com.ordensservico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ordensservico.model.Setor;

public interface SetorRepository extends JpaRepository<Setor, Integer> {
}