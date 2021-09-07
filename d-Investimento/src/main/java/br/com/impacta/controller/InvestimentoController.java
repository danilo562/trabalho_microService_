package br.com.impacta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.impacta.model.Investimento;
import br.com.impacta.proxy.ContaCorrenteProxy;
import br.com.impacta.proxy.MovContaProxy;
import br.com.impacta.repository.investimentoRepository;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("investimento")
public class InvestimentoController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private investimentoRepository repository;
	
	@Autowired
	private ContaCorrenteProxy proxy_contaCorrente;
	
	@Autowired
	private MovContaProxy proxy_movConta;
	
	@GetMapping(value = "/pesq_inv_doc/{doc}")
	@Retry(name ="default")
	//@CircuitBreaker(name = "default" ,fallbackMethod = "investimento_fora")
	@RateLimiter(name ="default")
	public Investimento getInvestimento(@PathVariable("doc") String doc) {
		var invest = repository.findByDoc(doc);
		if (invest == null) throw new RuntimeException("Nao Encontrou o Documento ============== <<<<<<<<<<<");
		
		var port = environment.getProperty("local.server.port");
		
		
		invest.setPorta(port);

		return invest;
		
	}
	
	@GetMapping(value = "/pesq_inv_id/{id}")
	@Retry(name ="default")
	//@CircuitBreaker(name = "default" ,fallbackMethod = "investimento_fora")
	@RateLimiter(name ="default")
	public Investimento getInvestimentoId(@PathVariable("id") Long id) {
		Investimento invest = repository.findById(id).orElseThrow();
		if (invest == null) throw new RuntimeException("Nao Encontrou o Documento ============== <<<<<<<<<<<");
		
		var port = environment.getProperty("local.server.port");
		
		
		invest.setPorta(port);

		
		return (Investimento) invest;
		
	}
	
	@PutMapping(value = "/incluir_saldo/{doc}/{saldo}")
	@Retry(name ="default")
	//@CircuitBreaker(name = "default" ,fallbackMethod = "investimento_fora")
	@RateLimiter(name ="default")
	public Investimento setIncluirSaldo(
			@PathVariable(value = "doc") String doc,
    		@PathVariable(value = "saldo")  Double saldo
            ) {
		
		Investimento invest = repository.findByDoc(doc);
		
		var novo_saldo = invest.getSaldo() + saldo;
		
		invest.setSaldo(novo_saldo);
        var port = environment.getProperty("local.server.port");
	
		invest.setPorta(port);
		
		Investimento update = repository.save(invest);
		
		return update;
	}
	
	
	@PutMapping(value = "/retira_saldo/{doc}/{saldo}")
	@Retry(name ="default")
	//@CircuitBreaker(name = "default" ,fallbackMethod = "investimento_fora")
	@RateLimiter(name ="default")
	public Investimento setRetiraSaldo(
			@PathVariable(value = "doc") String doc,
    		@PathVariable(value = "saldo")  Double saldo
            ) {
		
		Investimento invest = repository.findByDoc(doc);
		
		var novo_saldo = invest.getSaldo() - saldo;
		
		invest.setSaldo(novo_saldo);
        var port = environment.getProperty("local.server.port");
		
		invest.setPorta(port);
		
		Investimento update = repository.save(invest);
		
		proxy_contaCorrente.updatecredito(update.getId(), saldo);
		
		 proxy_movConta.criaMovConta(update.getId(),saldo,
                 "investimento",doc,"debitando saldo de investimento para credito em conta corrente");
		
	    proxy_movConta.criaMovConta(update.getId(),saldo,
	    		                      "contaCorrente",doc,"Creditando saldo em contaCorrente retirando da conta de INVESTIMENTO");
		
		return update;
	}
	

}
