package controle;

import java.util.ArrayList;
import java.util.Arrays;
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
	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	private Funcionario funcionario = new Funcionario();
	private double salarioFuncionario;
	
	
	
	public void atualizarValorPagamento() {
        if (funcionario != null) {
            salarioFuncionario = funcionario.getSalario();
        } else {
            salarioFuncionario = 0.0; // Ou outro valor padrão caso nenhum funcionário seja selecionado
        }
    }

	public double getSalarioFuncionario() {
		return salarioFuncionario;
	}

	public void setSalarioFuncionario(double salarioFuncionario) {
		this.salarioFuncionario = salarioFuncionario;
	}

	@PostConstruct
	public void iniciar() {
		funcionarios = funcionarioService.listAll();
		
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
}
