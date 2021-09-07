package br.com.impacta.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.impacta.response.CriaMovConta;

@FeignClient(name = "mov-conta")
public interface MovContaProxy {
	
	@PostMapping("/mov-conta/criar_mov/{id}/{saldo}/{tipo}/{doc}/{acao}")
	public CriaMovConta criaMovConta(@PathVariable(value = "id") Long id_cont,
    		@PathVariable(value = "saldo")  Double saldo,
    				@PathVariable("tipo") String tipo,
    				@PathVariable("doc") String doc,
    				@PathVariable("acao") String acao);

}
