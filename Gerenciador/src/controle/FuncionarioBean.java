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
import service.FuncionarioService;


@ViewScoped
@ManagedBean
public class FuncionarioBean {
	@EJB
	private FuncionarioService funcionarioService;
	
	private Funcionario funcionario = new Funcionario();
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
	private Boolean gravar = true; 
	private String texto;
	
	
	
	public void pesquisar() {
		funcionarios = funcionarioService.listarFuncionarioPeloNomeLike(texto);
	}
	
	private void atualizarLista() {
		funcionarios = funcionarioService.listAll();
	}
	
	@PostConstruct
	private void inicializar() {
		atualizarLista();
	}
	
	public void gravar() {
		funcionarioService.create(funcionario);
		FacesContext.getCurrentInstance().
			addMessage("msg1", new FacesMessage("Funcionário gravado com Sucesso!"));
		funcionario = new Funcionario();
		atualizarLista();
		gravar = true;
	}
	
	public void atualizar() {
		funcionarioService.merge(funcionario);
		FacesContext.getCurrentInstance().
		addMessage("msg1", new FacesMessage("Funcionario atualizado com Sucesso!"));
		funcionario = new Funcionario();
		atualizarLista();
		gravar = true;
	}
	
	public void excluir(Funcionario f) {
		funcionarioService.remove(f);
		atualizarLista();
		FacesContext.getCurrentInstance().
		addMessage("msg1", new FacesMessage("Funcionário removido com Sucesso!"));
	}
	
	public void carregarFuncionario(Funcionario f) {
		funcionario = f;
		gravar = false;
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

	public Boolean getGravar() {
		return gravar;
	}

	public void setGravar(Boolean gravar) {
		this.gravar = gravar;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

   
}

