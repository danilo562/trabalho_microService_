package br.com.impacta.controller;

import java.lang.annotation.Retention;
import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
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
import br.com.impacta.proxy.MovContaProxy;
import br.com.impacta.repository.ContaCorrenteRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="ContaCorrente endpoint")
@RestController
@RequestMapping("conta-corrente")
public class ContaCorrenteController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private ContaCorrenteRepository repository;
	
	@Autowired
	private InvestimentoProxy proxy_invst;
	
	@Autowired
	private MovContaProxy proxy_movConta;
	
	private Logger logger= LoggerFactory.getLogger(ContaCorrenteController.class);
	
	@Operation(summary = "Pesquisa conta pelo documento ")
	@GetMapping(value = "/pesq_doc/{doc}")
	@Retry(name ="default")
	//@CircuitBreaker(name = "default" ,fallbackMethod = "investimento_fora")
	@RateLimiter(name ="default")
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
	@Retry(name ="default")
	//@CircuitBreaker(name = "default" ,fallbackMethod = "investimento_fora")
	@RateLimiter(name ="default")
	public ContaCorrenteXInvestimento getContXInvest(@PathVariable("doc") String doc) {
		logger.info("Request para getContXInvest recebido ");
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
	
	
	public String investimento_fora(Exception ex) {
		return "Verifique o microServiço INVESTIMENTO provavelmente esta fora do ar.";
	}
	

	  @PutMapping("/credito/{id}/{saldo}")
	  @Retry(name ="default")
		//@CircuitBreaker(name = "default" ,fallbackMethod = "investimento_fora")
		@RateLimiter(name ="default")
	    public ContaCorrente updatecredito(@PathVariable(value = "id") Long noteId,
	    		@PathVariable(value = "saldo")  Double saldo
	                                            ) {

		  ContaCorrente note = repository.findById(noteId).orElseThrow();

		  
		  var novo_saldo = note.getSaldo() + saldo;
	      note.setSaldo(novo_saldo);
	      
	      proxy_movConta.criaMovConta(note.getId(),note.getSaldo(),
                  "credito",note.getDoc(), " Valor creditado em conta corrente ");

	        ContaCorrente updatedNote = repository.save(note);
	        return updatedNote;
	    }
	
	  
	  @PutMapping("/debito/{id}/{saldo}")
	  @Retry(name ="default")
		//@CircuitBreaker(name = "default" ,fallbackMethod = "investimento_fora")
		@RateLimiter(name ="default")
	    public ContaCorrente updateDebito(@PathVariable(value = "id") Long noteId,
	    		@PathVariable(value = "saldo")  Double saldo
	                                            ) {

		  ContaCorrente note = repository.findById(noteId).orElseThrow();

		  
		  var novo_saldo = note.getSaldo() - saldo;
	      note.setSaldo(novo_saldo);

	        ContaCorrente updatedNote = repository.save(note);
	        
	        proxy_movConta.criaMovConta(note.getId(),note.getSaldo(),
	                  "debito",note.getDoc()," Valor debitado de conta corrente ");
	        
	        return updatedNote;
	    }
	  
	  
	    @PutMapping("/investir/{doc}/{saldo}")
	    @Retry(name ="default")
		//@CircuitBreaker(name = "default" ,fallbackMethod = "investimento_fora")
		@RateLimiter(name ="default")
	    public ContaCorrente investiValor(@PathVariable("doc") String doc,
	    		@PathVariable(value = "saldo")  Double saldo
	                                            ) {

		  var conta = repository.findByDoc(doc);
		  var novo_saldo = conta.getSaldo() - saldo;

		  conta.setSaldo(novo_saldo);
	      var updatedNote = repository.save(conta);
	      var investimento = proxy_invst.setIncluirSaldo(doc,saldo);
	      
	      proxy_movConta.criaMovConta(conta.getId(),conta.getSaldo(),
                  "debito",conta.getDoc(), " Valor debitado de conta corrente para investimento ");
	      
	      proxy_movConta.criaMovConta(investimento.getId(),investimento.getSaldo(),
                  "credito",investimento.getDoc()," Valor creditado em conta investimento ");
	      
	        return updatedNote;
	    }
	
	
	
	

}
