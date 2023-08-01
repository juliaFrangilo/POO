package modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Pagamento {
	
	@Id @GeneratedValue
	private Long id;
	
	private Double valor;
	private Double bonus;
	private Date dataPagemento;
	private int mesReferente;
	private int anoReferente;
	
	
	public Pagamento () {
		
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
	public Date getDataPagemento() {
		return dataPagemento;
	}
	public void setDataPagemento(Date dataPagemento) {
		this.dataPagemento = dataPagemento;
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