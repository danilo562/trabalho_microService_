package br.com.impacta.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import br.com.impacta.response.ContaCorrente;

@FeignClient(name = "conta-corrente")
public interface ContaCorrenteProxy {
	
	
	@GetMapping(value = "/conta-corrente/pesq_doc/{doc}")
	public ContaCorrente getConta(
			@PathVariable("doc") String doc
			);
	
	 @PutMapping("/conta-corrente/debito/{id}/{saldo}")
	    public ContaCorrente updateDebito(@PathVariable(value = "id") Long noteId,
	    		@PathVariable(value = "saldo")  Double saldo
	                                            );

}
