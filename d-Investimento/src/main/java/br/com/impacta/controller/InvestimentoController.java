package br.com.impacta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.impacta.model.Investimento;
import br.com.impacta.repository.investimentoRepository;

@RestController
@RequestMapping("investimento")
public class InvestimentoController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private investimentoRepository repository;
	
	@GetMapping(value = "/pesq_inv_doc/{doc}")
	public Investimento getInvestimento(@PathVariable("doc") String doc) {
		var invest = repository.findByDoc(doc);
		if (invest == null) throw new RuntimeException("Nao Encontrou o Documento ============== <<<<<<<<<<<");
		
		var port = environment.getProperty("local.server.port");
		
		
		invest.setPorta(port);

		return invest;
		
	}
	
	@GetMapping(value = "/pesq_inv_id/{id}")
	public Investimento getInvestimentoId(@PathVariable("id") Long id) {
		Investimento invest = repository.findById(id).orElseThrow();
		if (invest == null) throw new RuntimeException("Nao Encontrou o Documento ============== <<<<<<<<<<<");
		
		var port = environment.getProperty("local.server.port");
		
		
		invest.setPorta(port);

		
		return (Investimento) invest;
		
	}
	

}
