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
public class PagamentoBean {
	
	@EJB
	private PagamentoService pagamentoService;
	
	@EJB
	private FuncionarioService funcionarioService;
	
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	private List<Pagamento> pagamentos = new ArrayList<Pagamento>();
	private Pagamento pagamento = new Pagamento();
	
	private Funcionario funcionario = new Funcionario();
	private long idFuncionario;
	private double salarioFuncionario;
	private double bonus;	
	private double valor;
	private boolean gravar;
	

	
	@PostConstruct
	public void iniciar() {
		funcionarios = funcionarioService.listAll();
		
	}
	
	private void atualizarLista() {
		pagamentos = pagamentoService.listAll();
	}
	
	public void selecionaFuncionario() {
		funcionario = funcionarioService.obtemPorId(idFuncionario);
		salarioFuncionario = funcionario.getSalario();
		valor = salarioFuncionario;
	}

	public void geraBonus() {
		
        if (funcionario != null) {

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
        bonus = 0.0;
      }
    } 
	
	public void gravar() {
		if (idFuncionario == 0  ) {
			 FacesContext.getCurrentInstance().
			    addMessage("msg1", new FacesMessage("Selecione o funcionario"));
		 }else{		
			 
		    funcionario.getPagamentos().add(pagamento);
		    funcionarioService.merge(funcionario);
		    pagamentoService.merge(pagamento);
		    FacesContext.getCurrentInstance().
		    addMessage("msg1", new FacesMessage("Pagamento gravado com sucesso"));
		    funcionario = new Funcionario();
		    pagamento = new Pagamento();
		    atualizarLista();
		    idFuncionario = 0L;
		 }
   }
	
	public void excluirPagamento(Pagamento pag) {
		pagamentoService.remove(pag);
		atualizarLista();
		FacesContext.getCurrentInstance().
		addMessage("msg1", new FacesMessage("Pagamento removido com sucesso!"));
	}
    
	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

	public long getIdFunfionario() {
		return idFuncionario;
	}

	public void setIdFunfionario(long idFuncionario) {
		this.idFuncionario = idFuncionario;
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
