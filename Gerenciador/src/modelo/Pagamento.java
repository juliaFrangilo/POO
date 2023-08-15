package modelo;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;



@Entity
public class Pagamento {
	
	@Id @GeneratedValue
	private Long id;
	
	private Double valor;
	private Double bonus;
	private Date dataPagamento;
	private Integer mesReferente;
	private Integer anoReferente;
	
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
	
	public Integer getMesReferente() {
		return mesReferente;
	}

	public void setMesReferente(Integer mesReferente) {
		this.mesReferente = mesReferente;
	}

	public Integer getAnoReferente() {
		return anoReferente;
	}

	public void setAnoReferente(Integer anoReferente) {
		this.anoReferente = anoReferente;
	}

	public Double getTotalPago(Double valor, Double bonus) {
		return valor + bonus;
	}

	

}
