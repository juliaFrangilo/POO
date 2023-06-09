package controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
	private Double salarioInicial = 0.0;
	private Double salarioFinal = 0.0;
	
	@PostConstruct
	public void iniciar() {
		filiais = filialService.listAll();
	}
	
	public void gerarRelatorio(){
		System.out.println(salarioInicial);
		System.out.println(salarioFinal);
		System.out.println(idFilial);
		if(idFilial == 0 && salarioInicial != 0.0 && salarioFinal != 0.0) {
			funcionarios = funcionarioService.listarFuncionarioValorSalarialSemFilial(salarioInicial,salarioFinal);
		}else if(idFilial != 0 && salarioInicial != 0.0 && salarioFinal != 0.0) {
			funcionarios = funcionarioService.listarFuncionarioValorSalarialComFilial(salarioInicial,salarioFinal,idFilial);
		}else if(idFilial == 0 && salarioInicial == 0.0 && salarioFinal == 0.0) {
			funcionarios = funcionarioService.ordernaNomeFuncionario();
		}else if(idFilial != 0 && salarioInicial == 0.0 && salarioFinal == 0.0) {
			funcionarios = funcionarioService.listarFuncionarioPorFilial(idFilial);
		}
		idFilial = 0L;
		salarioInicial = 0.0;
		salarioFinal = 0.0;
		
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
