package br.com.impacta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.impacta.model.Investimento;

public interface investimentoRepository extends JpaRepository<Investimento,Long> {

	
	Investimento findByDoc(String doc);
	

}
