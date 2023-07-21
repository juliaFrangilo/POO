package controle;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Filial;
import modelo.Funcionario;
import service.FilialService;
import service.FuncionarioService;

@ViewScoped
@ManagedBean
public class RelatorioBean {
	@EJB
	private FuncionarioService funcionarioService;
	
	@EJB
	private FilialService filialService;
	
	private Funcionario funcionario = new Funcionario();
	
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	private List<Filial> filiais = new ArrayList<Filial>();
	
	private Long idFilial;
	private String texto;
	private Double salarioInicial ;
	private Double salarioFinal ;
	
	
	
	@PostConstruct
	public void iniciar() {
		filiais = filialService.listAll();
	}
	public void gerarRelatorio() {
		System.out.println(salarioInicial);
		System.out.println(salarioFinal);
		
		//filtra funcionarios por salario
		if (idFilial == 0 && salarioInicial != 0.0 && salarioFinal != 0.0) {
	        funcionarios = funcionarioService.listarFuncionarioValorSalarialSemFilial(salarioInicial, salarioFinal);
	    }//filtra funcionarios por salario e por filial 
		else if (idFilial != 0 && salarioInicial != 0.0 && salarioFinal != 0.0) {
	        funcionarios = funcionarioService.listarFuncionarioValorSalarialComFilial(salarioInicial, salarioFinal, idFilial);
	    }//filtra funcionarios por salario final 
		else if (idFilial == 0 && salarioInicial == 0.0 && salarioFinal != 0.0) {
	        funcionarios = funcionarioService.listarFuncionarioValorSalarialSemFilial(salarioInicial, salarioFinal);
	    }//filtra funcionarios por salario final e por filial 
		else if (idFilial != 0 && salarioInicial == 0.0 && salarioFinal != 0.0) {
	        funcionarios = funcionarioService.listarFuncionarioValorSalarialComFilial(salarioInicial, salarioFinal, idFilial);
	    }//sem filtro 
		else if(salarioFinal == 0.0 && idFilial == 0) {
			//verifica se o salario inicial foi preenchido e avisa que ele foi desconsiderado por que o final não foi preenchido
	    	if(salarioInicial != 0.0) {
	    		FacesContext.getCurrentInstance().
			    addMessage("msg1", new FacesMessage("O filtro salarial foi desconsiderado por que o valor final não foi definido."));
	    	}
	    	funcionarios = funcionarioService.listarFuncionarioPorFilialOrdemSalario(0L);
	    }//sem filtro 
		else if(salarioFinal == 0.0 && idFilial != 0) {
			//verifica se o salario inicial foi preenchido e avisa que ele foi desconsiderado por que o final não foi preenchido
	    	if(salarioInicial != 0.0) {
	    		FacesContext.getCurrentInstance().
			    addMessage("msg1", new FacesMessage("O filtro salarial foi desconsiderado por que o valor final não foi definido."));
	    	}
	    	
	    	funcionarios = funcionarioService.listarFuncionarioPorFilialOrdemSalario(idFilial);
	    }

	    salarioInicial = null;
	    salarioFinal = null;
	    idFilial = 0L;
	    
	    if (funcionarios.isEmpty()) {
			 FacesContext.getCurrentInstance().
			    addMessage("msg1", new FacesMessage("Nenhum funcionário encontrado para essa pesquisa."));
		 }
	}	
	// getters e setters
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	public List<Filial> getFiliais() {
		return filiais;
	}
	public void setFiliais(List<Filial> filiais) {
		this.filiais = filiais;
	}
	public Long getIdFilial() {
		return idFilial;
	}
	public void setIdFilial(Long idFilial) {
		this.idFilial = idFilial;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Double getSalarioInicial() {
		return salarioInicial;
	}
	public void setSalarioInicial(Double salarioInicial) {
		this.salarioInicial = salarioInicial;
	}
	public Double getSalarioFinal() {
		return salarioFinal;
	}
	public void setSalarioFinal(Double salarioFinal) {
		this.salarioFinal = salarioFinal;
	}
}
