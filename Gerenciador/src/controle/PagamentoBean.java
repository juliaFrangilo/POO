package controle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import modelo.Funcionario;
import service.FuncionarioService;

@ViewScoped
@ManagedBean
public class PagamentoBean {
	@EJB
	private FuncionarioService funcionarioService;
	
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
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
}
