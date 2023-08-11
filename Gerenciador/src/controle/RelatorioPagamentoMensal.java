package controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;

import modelo.Funcionario;
import modelo.Pagamento;
import service.FilialService;
import service.FuncionarioService;

@ViewScoped
@ManagedBean
public class RelatorioPagamentoMensal {
	@EJB
	private FuncionarioService funcionarioService;
	
	@EJB
	private FilialService pagamentoService;
	
	private Funcionario funcionario = new Funcionario();
	
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	private List<Pagamento> pagamentos = new ArrayList<Pagamento>();

}
