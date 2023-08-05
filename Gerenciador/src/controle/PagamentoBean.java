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



@ViewScoped
@ManagedBean
public class PagamentoBean {
	
	@EJB
	private FuncionarioService funcionarioService;
	
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	private Pagamento pagamento = new Pagamento();
	
	private Funcionario funcionario = new Funcionario();
	private Funcionario func;
	private double salarioFuncionario;
	private double bonus;	
	private double valor;
	private boolean gravar;
	
	
	 
	
	@PostConstruct
	public void iniciar() {
		funcionarios = funcionarioService.listAll();
		
	}
	
	
	
	
	public void atualizarValorPagamento() {
		
        if (func != null) {
        	 salarioFuncionario = func.getSalario();
          
          
        }
        valor = salarioFuncionario;
    } 
    
    

	
	public double getSalarioFuncionario() {
		return salarioFuncionario;
	}

	public void setSalarioFuncionario(double salarioFuncionario) {
		this.salarioFuncionario = salarioFuncionario;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public Funcionario getFunc() {
		return func;
	}

	public void setFunc(Funcionario func) {
		this.func = func;
	}


	public double getValor() {
		return valor;
	}


	public void setValor(double valor) {
		this.valor = valor;
	}

	public boolean isGravar() {
		return gravar;
	}


	public void setGravar(boolean gravar) {
		this.gravar = gravar;
	}
	
	 

}
