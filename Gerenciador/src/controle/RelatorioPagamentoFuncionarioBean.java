package controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Funcionario;
import modelo.Pagamento;
import service.FuncionarioService;
import service.PagamentoService;

@ViewScoped
@ManagedBean
public class RelatorioPagamentoFuncionarioBean{
	
	@EJB
	private PagamentoService pagamentoService;
	
	@EJB
	private FuncionarioService funcionarioService;
	
	private List<Pagamento> pagamentos ;
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
	private Funcionario funcionario ;
	private Long idFunc = 0L;
	
	
	@PostConstruct
	public void iniciar() {
		funcionarios = funcionarioService.listAll();
		
	}
	
	public void gerarRelatorioPagamentosPorFuncionario() {
	      if (idFunc != 0L) {
	            pagamentos = pagamentoService.obterPagamentosPorFuncionarioOrdenados(idFunc);
	           
	      } else {
	    	  FacesContext.getCurrentInstance().addMessage("msg1", new FacesMessage("Selecione um funcionário."));
	      }
	}
	public PagamentoService getPagamentoService() {
		return pagamentoService;
	}

	public void setPagamentoService(PagamentoService pagamentoService) {
		this.pagamentoService = pagamentoService;
	}
	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
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



	public Long getIdFunc() {
		return idFunc;
	}



	public void setIdFunc(Long idFunc) {
		this.idFunc = idFunc;
	}
	
	
}
