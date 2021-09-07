package br.com.impacta.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import br.com.impacta.response.Investimento;

@FeignClient(name = "investimento")
public interface InvestimentoProxy {
	
	@GetMapping(value = "/investimento/pesq_inv_doc/{doc}")
	public Investimento getInvestimento(
			@PathVariable("doc") String doc);
	
	
	@PutMapping(value = "/investimento/retira_saldo/{doc}/{saldo}")
	public Investimento setRetiraSaldo(
			@PathVariable(value = "doc") String doc,
    		@PathVariable(value = "saldo")  Double saldo
            );
	
	@PutMapping(value = "/investimento/incluir_saldo/{doc}/{saldo}")
	public Investimento setIncluirSaldo(
			@PathVariable(value = "doc") String doc,
    		@PathVariable(value = "saldo")  Double saldo
            );

}
