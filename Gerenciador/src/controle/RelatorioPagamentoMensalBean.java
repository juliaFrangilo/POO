package controle;


import java.util.ArrayList;
import java.util.List;

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
public class RelatorioPagamentoMensalBean {
	
	@EJB
	 private PagamentoService pagamentoService;
	
	@EJB
	 private FuncionarioService funcionarioService;
	 
	 
	    private Integer mes;
	    private Integer ano;
	    private List<Pagamento> pagamentos = new ArrayList<Pagamento>();
	    private double totalPagamentos;
	    private List<Funcionario> funcionariosPagos;
	  

	
		public void atualizarLista() {
			 calcularTotalPagamentos();
		}
		
	    public void gerarRelatorioMensal() {
	    	 pagamentos = pagamentoService.obterFuncionariosPagosNoPeriodo(mes, ano);

	    	    if (pagamentos.isEmpty()) {
	    	        FacesContext context = FacesContext.getCurrentInstance();
	    	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso! Nenhum pagamento encontrado neste período.",
	    	            "Aviso."));
	    	    }

	    	    calcularTotalPagamentos();
	    	}
	    
	    private void calcularTotalPagamentos() {
	        totalPagamentos = 0.0;
	        for (Pagamento pagamento : pagamentos) {
	            totalPagamentos += pagamento.getTotalPago(pagamento.getValor(), pagamento.getBonus()); 
	    }
	}   

		public PagamentoService getPagamentoService() {
			return pagamentoService;
		}


		public void setPagamentoService(PagamentoService pagamentoService) {
			this.pagamentoService = pagamentoService;
		}

		public Integer getMes() {
			return mes;
		}

		public void setMes(Integer mes) {
			this.mes = mes;
		}

		public Integer getAno() {
			return ano;
		}

		public void setAno(Integer ano) {
			this.ano = ano;
		}

		public List<Pagamento> getPagamentos() {
			return pagamentos;
		}


		public void setPagamentos(List<Pagamento> pagamentos) {
			this.pagamentos = pagamentos;
		}

		public double getTotalPagamentos() {
			return totalPagamentos;
		}

		public void setTotalPagamentos(double totalPagamentos) {
			this.totalPagamentos = totalPagamentos;
		}

		public List<Funcionario> getFuncionariosPagos() {
			return funcionariosPagos;
		}

		public void setFuncionariosPagos(List<Funcionario> funcionariosPagos) {
			this.funcionariosPagos = funcionariosPagos;
		}
		
	    
}