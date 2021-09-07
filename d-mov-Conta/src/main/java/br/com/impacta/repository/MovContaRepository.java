package br.com.impacta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.impacta.model.CriaMovConta;

public interface MovContaRepository extends JpaRepository<CriaMovConta, Long> {
	
	//@Query("select * from movconta m where m.doc= ?1")
	List<CriaMovConta> findByDoc(String doc);
}
