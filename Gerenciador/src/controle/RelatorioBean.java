package controle;

import java.math.BigDecimal;
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
	private BigDecimal salarioInicial;
	private BigDecimal salarioFinal;
	
	
	
	@PostConstruct
	public void iniciar() {
		filiais = filialService.listAll();
	}
	public void gerarRelatorio() {
	    if (idFilial == 0 && salarioInicial != null && salarioFinal != null) {
	        funcionarios = funcionarioService.listarFuncionarioValorSalarialSemFilial(salarioInicial, salarioFinal);
	    } else if (idFilial != 0 && salarioInicial != null && salarioFinal != null) {
	        funcionarios = funcionarioService.listarFuncionarioValorSalarialComFilial(salarioInicial, salarioFinal, idFilial);
	    } else if (idFilial == 0 && salarioInicial == null && salarioFinal == null) {
	        funcionarios = funcionarioService.ordernaNomeFuncionario();
	    } else if (idFilial != 0 && salarioInicial == null && salarioFinal == null) {
	        funcionarios = funcionarioService.listarFuncionarioPorFilial(idFilial);
	  
	    }  

	    salarioInicial = null;
	    salarioFinal = null;
	    idFilial = 0L;
	    
	    if (funcionarios.isEmpty()) {
			 FacesContext.getCurrentInstance().
			    addMessage("msg1", new FacesMessage("Nenhum Funcionário encontrado com essa faixa salarial."));
		 }
	}	
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
	public BigDecimal getSalarioInicial() {
		return salarioInicial;
	}
	public void setSalarioInicial(BigDecimal salarioInicial) {
		this.salarioInicial = salarioInicial;
	}
	public BigDecimal getSalarioFinal() {
		return salarioFinal;
	}
	public void setSalarioFinal(BigDecimal salarioFinal) {
		this.salarioFinal = salarioFinal;
	}
	
}
