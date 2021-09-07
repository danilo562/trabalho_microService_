package br.com.impacta.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Transient;

public class ContaCorrenteXInvestimento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long id_conta;
	private Long id_invest;
	private Double saldo_conta;
	
    
	private String porta_conta;
    
  
	private String tipo_conta;
    
   
	private String doc_conta;
    private Double saldo_invest;
	private String porta_invest;

	private String tipo_invest;
    

	private String doc_invest;

	public ContaCorrenteXInvestimento() {
	}

	public ContaCorrenteXInvestimento(Long id, Long id_conta, Long id_invest, Double saldo_conta, String porta_conta,
			String tipo_conta, String doc_conta, Double saldo_invest, String porta_invest, String tipo_invest,
			String doc_invest) {
		this.id = id;
		this.id_conta = id_conta;
		this.id_invest = id_invest;
		this.saldo_conta = saldo_conta;
		this.porta_conta = porta_conta;
		this.tipo_conta = tipo_conta;
		this.doc_conta = doc_conta;
		this.saldo_invest = saldo_invest;
		this.porta_invest = porta_invest;
		this.tipo_invest = tipo_invest;
		this.doc_invest = doc_invest;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_conta() {
		return id_conta;
	}

	public void setId_conta(Long id_conta) {
		this.id_conta = id_conta;
	}

	public Long getId_invest() {
		return id_invest;
	}

	public void setId_invest(Long id_invest) {
		this.id_invest = id_invest;
	}

	public Double getSaldo_conta() {
		return saldo_conta;
	}

	public void setSaldo_conta(Double saldo_conta) {
		this.saldo_conta = saldo_conta;
	}

	public String getPorta_conta() {
		return porta_conta;
	}

	public void setPorta_conta(String porta_conta) {
		this.porta_conta = porta_conta;
	}

	public String getTipo_conta() {
		return tipo_conta;
	}

	public void setTipo_conta(String tipo_conta) {
		this.tipo_conta = tipo_conta;
	}

	public String getDoc_conta() {
		return doc_conta;
	}

	public void setDoc_conta(String doc_conta) {
		this.doc_conta = doc_conta;
	}

	public Double getSaldo_invest() {
		return saldo_invest;
	}

	public void setSaldo_invest(Double saldo_invest) {
		this.saldo_invest = saldo_invest;
	}

	public String getPorta_invest() {
		return porta_invest;
	}

	public void setPorta_invest(String porta_invest) {
		this.porta_invest = porta_invest;
	}

	public String getTipo_invest() {
		return tipo_invest;
	}

	public void setTipo_invest(String tipo_invest) {
		this.tipo_invest = tipo_invest;
	}

	public String getDoc_invest() {
		return doc_invest;
	}

	public void setDoc_invest(String doc_invest) {
		this.doc_invest = doc_invest;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(doc_conta, doc_invest, id, id_conta, id_invest, porta_conta, porta_invest, saldo_conta,
				saldo_invest, tipo_conta, tipo_invest);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaCorrenteXInvestimento other = (ContaCorrenteXInvestimento) obj;
		return Objects.equals(doc_conta, other.doc_conta) && Objects.equals(doc_invest, other.doc_invest)
				&& Objects.equals(id, other.id) && Objects.equals(id_conta, other.id_conta)
				&& Objects.equals(id_invest, other.id_invest) && Objects.equals(porta_conta, other.porta_conta)
				&& Objects.equals(porta_invest, other.porta_invest) && Objects.equals(saldo_conta, other.saldo_conta)
				&& Objects.equals(saldo_invest, other.saldo_invest) && Objects.equals(tipo_conta, other.tipo_conta)
				&& Objects.equals(tipo_invest, other.tipo_invest);
	}
	
	

}
