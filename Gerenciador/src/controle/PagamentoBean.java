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
	private double salarioFuncionario;
	private double bonus;
	 
	
	@PostConstruct
	public void iniciar() {
		funcionarios = funcionarioService.listAll();
		
	}
	
	public void atualizarValorPagamento() {
		
        if (funcionario != null) {
            salarioFuncionario = funcionario.getSalario();
          
            switch (funcionario.getCargo()) {
            case COMUM:
                bonus = salarioFuncionario * 0.1;
                break;
            case COORDENADOR:
                bonus = salarioFuncionario * 0.15;
                break;
            case GERENTE:
                bonus = salarioFuncionario * 0.2;
                break;
            default:
                bonus = 0.0;
                
        }
    } else {
        salarioFuncionario = 0.0;
        bonus = 0.0;
      }
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
	
}
