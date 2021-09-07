package br.com.impacta.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.impacta.response.Investimento;

@FeignClient(name = "investimento")
public interface InvestimentoProxy {
	
	@GetMapping(value = "/investimento/pesq_inv_doc/{doc}")
	public Investimento getInvestimento(
			@PathVariable("doc") String doc);

}
