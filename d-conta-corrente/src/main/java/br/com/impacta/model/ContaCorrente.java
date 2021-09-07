package br.com.impacta.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.boot.autoconfigure.domain.EntityScan;



@Entity(name="conta")
public class ContaCorrente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Double saldo;
	
    @Transient
	private String porta;
    
    @Column(nullable = false)
	private String tipo;
    
    @Column(nullable = false)
	private String doc;
	
	public ContaCorrente(Long id, Double saldo, String porta) {
		this.id = id;
		this.saldo = saldo;
		this.porta = porta;
	}

	public String getPorta() {
		return porta;
	}

	public void setPorta(String porta) {
		this.porta = porta;
	}

	public ContaCorrente() {
	}

	public ContaCorrente(Long id, Double saldo, String porta, String tipo, String doc) {
		this.id = id;
		this.saldo = saldo;
		this.porta = porta;
		this.tipo = tipo;
		this.doc = doc;
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

	public ContaCorrente(Long id, Double saldo) {
		this.id = id;
		this.saldo = saldo;
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
		ContaCorrente other = (ContaCorrente) obj;
		return Objects.equals(doc, other.doc) && Objects.equals(id, other.id) && Objects.equals(porta, other.porta)
				&& Objects.equals(saldo, other.saldo) && Objects.equals(tipo, other.tipo);
	}
	
	
	

}
