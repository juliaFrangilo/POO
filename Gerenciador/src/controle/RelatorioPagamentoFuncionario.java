package controle;

import java.util.ArrayList;
import java.util.List;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Funcionario;
import modelo.Pagamento;
import service.PagamentoService;

@ViewScoped
@ManagedBean
public class RelatorioPagamentoFuncionario{
	
	@EJB
	private PagamentoService pagamentoService;
	
	private List<Pagamento> pagamentos ;
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
	private Funcionario funcionario ;
	private Long idFunc = 0L;
	
	
	
	
	public void gerarRelatorioPagamentosPorFuncionario(Funcionario funcionario) {
	      if (funcionario != null) {
	            pagamentos = pagamentoService.obterPagamentosPorFuncionarioOrdenados(funcionario);
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
