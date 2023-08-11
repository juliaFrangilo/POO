package modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;



@Entity
public class Pagamento {
	
	@Id @GeneratedValue
	private Long id;
	
	private Double valor;
	private Double bonus;
	private Date dataPagamento;
	private int mesReferente;
	private int anoReferente;
	
	@OneToOne 
	private Funcionario funcionario;
	
	
	public Pagamento () {
		
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Double getBonus() {
		return bonus;
	}
	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagemento) {
		this.dataPagamento = dataPagemento;
	}
	public int getMesReferente() {
		return mesReferente;
	}
	public void setMesReferente(int mesReferente) {
		this.mesReferente = mesReferente;
	}
	public int getAnoReferente() {
		return anoReferente;
	}
	public void setAnoReferente(int anoReferente) {
		this.anoReferente = anoReferente;
	}

	

}
