package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Filial {
	
	@Id @GeneratedValue
	private Long id;
	
	private String nome;
	private String cnpj;
	
	@OneToOne
	private Endereco endereco;
	
	@Transient
	private Long totalFuncionarios = 0L;
	
	public Filial() {}
	
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public Long getTotalFuncionarios() {
		return totalFuncionarios;
	}

	public void setTotalFuncionarios(Long totalFuncionarios) {
		this.totalFuncionarios = totalFuncionarios;
	}
}
