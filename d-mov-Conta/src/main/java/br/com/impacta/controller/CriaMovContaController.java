package br.com.impacta.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.impacta.model.CriaMovConta;
import br.com.impacta.repository.MovContaRepository;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="mov-conta endpoint")
@RestController
@RequestMapping("mov-conta")
public class CriaMovContaController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private MovContaRepository repository;
	
	@PostMapping("/criar_mov/{id}/{saldo}/{tipo}/{doc}/{acao}")
	@Retry(name ="default")
	//@CircuitBreaker(name = "default" ,fallbackMethod = "investimento_fora")
	@RateLimiter(name ="default")
	public CriaMovConta criaMovConta(@PathVariable(value = "id") Long id_cont,
    		@PathVariable(value = "saldo")  Double saldo,
    				@PathVariable("tipo") String tipo,
    				@PathVariable("doc") String doc,
    				@PathVariable("acao") String acao){
		
		var dt = new Date();
		var port = environment.getProperty("local.server.port");
		
		CriaMovConta fat = new CriaMovConta();
		fat.setId_cont(id_cont);
		fat.setSaldo(saldo);
		fat.setTipo(tipo);
		fat.setDoc(doc);
		fat.setAcao(acao);
		fat.setData_mov(dt);
		
		var faturaCriada = repository.save(fat);
		
		faturaCriada.setPorta(port);
		
		return faturaCriada;
	}
	
	
	@GetMapping("/extrato_conta/{doc}")
	@Retry(name ="default")
	//@CircuitBreaker(name = "default" ,fallbackMethod = "investimento_fora")
	@RateLimiter(name ="default")
	public List<CriaMovConta> extratCont(@PathVariable("doc") String doc) {
		
		var mov = repository.findByDoc(doc);
		
		
		return  mov;
		
	}
	
//	@GetMapping("/extrato_conta/{doc}")
//	public List<CriaMovConta> extratCont(@PathVariable("doc") String doc) {
//		
//		List<CriaMovConta> extr = new ArrayList<CriaMovConta>();
//		var mov = repository.findByDoc(doc);
//		for(CriaMovConta item : mov) {
//			
//			
//		}
//		
//		extr.add(mov);
//		return extr;
//		
//	}
	
	

}
