package controle;


import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import modelo.Funcionario;
import modelo.Pagamento;
import service.FuncionarioService;
import service.PagamentoService;

@ViewScoped
@ManagedBean
public class RelatorioPagamentoMensal {
	
	@EJB
	 private PagamentoService pagamentoService;
	
	@EJB
	 private FuncionarioService funcionarioService;
	 
	 
	    

	    private int mes;
	    private int ano;
	    private List<Pagamento> pagamentos = new ArrayList<Pagamento>();
	    private double totalPagamentos;
	    private List<Funcionario> funcionariosPagos;
	  

	    @PostConstruct
		public void iniciar() {
			atualizarLista();
			
		}
		
		public void atualizarLista() {
			pagamentos = pagamentoService.listAll();
			 calcularTotalPagamentos();
		}
		
	    public void gerarRelatorioMensal() {
	      funcionariosPagos = pagamentoService.obterFuncionariosPagosNoPeriodo(mes, ano);
	        calcularTotalPagamentos();
	        }
	    
	    private void calcularTotalPagamentos() {
	        totalPagamentos = 0.0;
	        for (Pagamento pagamento : pagamentos) {
	            totalPagamentos += pagamento.getValor(); 
	    }
	}   

		public PagamentoService getPagamentoService() {
			return pagamentoService;
		}


		public void setPagamentoService(PagamentoService pagamentoService) {
			this.pagamentoService = pagamentoService;
		}


		public int getMes() {
			return mes;
		}


		public void setMes(int mes) {
			this.mes = mes;
		}


		public int getAno() {
			return ano;
		}


		public void setAno(int ano) {
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