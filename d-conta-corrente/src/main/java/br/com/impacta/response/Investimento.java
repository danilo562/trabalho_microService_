package br.com.impacta.response;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Transient;

public class Investimento implements Serializable{

	
	private static final long serialVersionUID = 1L;

    private Long id;

	private Double saldo;
	
	private String porta;
    
	private String tipo;
  
	private String doc;

	public Investimento() {
	}

	public Investimento(Long id, Double saldo, String porta, String tipo, String doc) {
		this.id = id;
		this.saldo = saldo;
		this.porta = porta;
		this.tipo = tipo;
		this.doc = doc;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getPorta() {
		return porta;
	}

	public void setPorta(String porta) {
		this.porta = porta;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(doc, id, porta, saldo, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Investimento other = (Investimento) obj;
		return Objects.equals(doc, other.doc) && Objects.equals(id, other.id) && Objects.equals(porta, other.porta)
				&& Objects.equals(saldo, other.saldo) && Objects.equals(tipo, other.tipo);
	}
	
	
	
}
