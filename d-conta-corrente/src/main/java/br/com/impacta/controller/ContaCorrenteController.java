package br.com.impacta.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.impacta.model.ContaCorrente;
import br.com.impacta.model.ContaCorrenteXInvestimento;
import br.com.impacta.proxy.InvestimentoProxy;
import br.com.impacta.repository.ContaCorrenteRepository;


@RestController
@RequestMapping("conta-corrente")
public class ContaCorrenteController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private ContaCorrenteRepository repository;
	
	@Autowired
	private InvestimentoProxy proxy_invst;
	
	
	@GetMapping(value = "/pesq_doc/{doc}")
	public ContaCorrente getConta(
			@PathVariable("doc") String doc
			
			) {

		var conta = repository.findByDoc(doc);
		if (conta == null) throw new RuntimeException("Nao Encontrou o Documento ============== <<<<<<<<<<<");
		
		var port = environment.getProperty("local.server.port");
		
		
		conta.setPorta(port);

		return conta;
		
		
	}
	
	@GetMapping(value = "/saldo_cont_inves/{doc}")
	public ContaCorrenteXInvestimento getContXInvest(@PathVariable("doc") String doc) {
		
		var investimento = proxy_invst.getInvestimento(doc);
		var conta = repository.findByDoc(doc);
		
		var port = environment.getProperty("local.server.port");
		ContaCorrenteXInvestimento response = new ContaCorrenteXInvestimento() ;
		response.setId_conta(conta.getId());
		response.setSaldo_conta(conta.getSaldo());
		response.setTipo_conta(conta.getTipo());
		response.setDoc_conta(conta.getDoc());
		response.setPorta_conta(port);
		
		response.setId_invest(investimento.getId());
		response.setSaldo_invest(investimento.getSaldo());
		response.setTipo_invest(investimento.getTipo());
		response.setDoc_invest(investimento.getDoc());
		response.setPorta_invest(investimento.getPorta());
		
		return response;
		
	}
	

	  @PutMapping("/credito/{id}/{saldo}")
	    public ContaCorrente updatecredito(@PathVariable(value = "id") Long noteId,
	    		@PathVariable(value = "saldo")  Double saldo
	                                            ) {

		  ContaCorrente note = repository.findById(noteId).orElseThrow();

		  
		  var novo_saldo = note.getSaldo() + saldo;
	      note.setSaldo(novo_saldo);

	        ContaCorrente updatedNote = repository.save(note);
	        return updatedNote;
	    }
	
	  
	  @PutMapping("/debito/{id}/{saldo}")
	    public ContaCorrente updateDebito(@PathVariable(value = "id") Long noteId,
	    		@PathVariable(value = "saldo")  Double saldo
	                                            ) {

		  ContaCorrente note = repository.findById(noteId).orElseThrow();

		  
		  var novo_saldo = note.getSaldo() - saldo;
	      note.setSaldo(novo_saldo);

	        ContaCorrente updatedNote = repository.save(note);
	        return updatedNote;
	    }
	  
	  
	  @PutMapping("/investir/{doc}/{saldo}")
	    public ContaCorrente investiValor(@PathVariable("doc") String doc,
	    		@PathVariable(value = "saldo")  Double saldo
	                                            ) {

		  var conta = repository.findByDoc(doc);
		  var novo_saldo = conta.getSaldo() - saldo;

		  conta.setSaldo(novo_saldo);
	      var updatedNote = repository.save(conta);
	      var investimento = proxy_invst.setIncluirSaldo(doc,saldo);
	      
	        return updatedNote;
	    }
	
	
	
	

}
