package br.com.impacta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.impacta.model.PagFatura;

public interface PagFaturaRepository extends JpaRepository<PagFatura, Long> {

	PagFatura findByDoc(String doc);
	
}
