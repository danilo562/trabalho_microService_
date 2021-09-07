package br.com.impacta.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name="fatura")
public class PagFatura implements Serializable{

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
    

    @Column(nullable = false)
	private String fat_pag;
    

    @Column(nullable = false)
	private String mes;


	public PagFatura() {
	}


	public PagFatura(Long id, Double saldo, String porta, String tipo, String doc, String fat_pag, String mes) {
		this.id = id;
		this.saldo = saldo;
		this.porta = porta;
		this.tipo = tipo;
		this.doc = doc;
		this.fat_pag = fat_pag;
		this.mes = mes;
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


	public String getFat_pag() {
		return fat_pag;
	}


	public void setFat_pag(String fat_pag) {
		this.fat_pag = fat_pag;
	}


	public String getMes() {
		return mes;
	}


	public void setMes(String mes) {
		this.mes = mes;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public int hashCode() {
		return Objects.hash(doc, fat_pag, id, mes, porta, saldo, tipo);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PagFatura other = (PagFatura) obj;
		return Objects.equals(doc, other.doc) && Objects.equals(fat_pag, other.fat_pag) && Objects.equals(id, other.id)
				&& Objects.equals(mes, other.mes) && Objects.equals(porta, other.porta)
				&& Objects.equals(saldo, other.saldo) && Objects.equals(tipo, other.tipo);
	}
    
    
	

}
