package br.com.impacta.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.impacta.model.PagFatura;
import br.com.impacta.proxy.ContaCorrenteProxy;
import br.com.impacta.proxy.MovContaProxy;
import br.com.impacta.repository.PagFaturaRepository;

@RestController
@RequestMapping("pag-fatura")
public class PagFaturaController {
	
	@Autowired
	private Environment environment;
	
	
	
	@Autowired
	private PagFaturaRepository repository_fat;
	
	@Autowired
	private ContaCorrenteProxy proxy_contaCorrente;
	
	@Autowired
	private MovContaProxy proxy_movConta;
	
	
	
	@PutMapping("/pag_fat/{doc}")
	public PagFatura pagFatura(@PathVariable("doc") String doc) {
		
		var fat = repository_fat.findByDoc(doc);
		var contaCorrente = proxy_contaCorrente.getConta(doc);
		
		
		if( fat.getSaldo()>contaCorrente.getSaldo()) {
			
			fat.setFat_pag("nao_pag");
			 proxy_movConta.criaMovConta(fat.getId(),fat.getSaldo(),
	                    "fatura-nao-pag",doc,"fatura nao foi paga, saldo em conta "+
			 contaCorrente.getSaldo() + " Valor fatura "+fat.getSaldo() );
			
		}else {
			
			fat.setFat_pag("sim");
			proxy_contaCorrente.updateDebito(contaCorrente.getId(), fat.getSaldo());
			
		    proxy_movConta.criaMovConta(contaCorrente.getId(),contaCorrente.getSaldo(),
		    		                      "contaCorrente",doc,"Debito contaCorrente pagamento fatura");
		    proxy_movConta.criaMovConta(fat.getId(),fat.getSaldo(),
                    "pag-fatura",doc,"fatura paga");
			
		}
		
		
		
		var fatPag = repository_fat.save(fat);
		
		var port = environment.getProperty("local.server.port");
		fatPag.setPorta(port);
		
		return fatPag;
	}
	

}
