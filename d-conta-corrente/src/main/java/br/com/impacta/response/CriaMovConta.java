package br.com.impacta.response;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;



public class CriaMovConta implements Serializable {


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
    private Long id_cont;
    
    @Column(nullable = false)
    private String acao;
    
    @Column(nullable = false)
    private Date data_mov;

	public CriaMovConta() {
	}

	public CriaMovConta(Long id, Double saldo, String porta, String tipo, String doc, Long id_cont, String acao,
			Date data_mov) {
		this.id = id;
		this.saldo = saldo;
		this.porta = porta;
		this.tipo = tipo;
		this.doc = doc;
		this.id_cont = id_cont;
		this.acao = acao;
		this.data_mov = data_mov;
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

	public Long getId_cont() {
		return id_cont;
	}

	public void setId_cont(Long id_cont) {
		this.id_cont = id_cont;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Date getData_mov() {
		return data_mov;
	}

	public void setData_mov(Date data_mov) {
		this.data_mov = data_mov;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(acao, data_mov, doc, id, id_cont, porta, saldo, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CriaMovConta other = (CriaMovConta) obj;
		return Objects.equals(acao, other.acao) && Objects.equals(data_mov, other.data_mov)
				&& Objects.equals(doc, other.doc) && Objects.equals(id, other.id)
				&& Objects.equals(id_cont, other.id_cont) && Objects.equals(porta, other.porta)
				&& Objects.equals(saldo, other.saldo) && Objects.equals(tipo, other.tipo);
	}

    
    
    
    
}
