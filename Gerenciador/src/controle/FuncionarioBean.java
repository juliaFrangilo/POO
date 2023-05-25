package controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Endereco;
import modelo.Filial;
import modelo.Funcionario;
import service.EnderecoService;
import service.FilialService;
import service.FuncionarioService;


@ViewScoped
@ManagedBean
public class FuncionarioBean {
	@EJB
	private FuncionarioService funcionarioService;
	
	@EJB
	private EnderecoService enderecoService;
	
	@EJB
	private FilialService filialService;
	
	private Funcionario funcionario = new Funcionario();
	private Endereco endereco = new Endereco();
	
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
	private List<Filial> filiais = new ArrayList<Filial>();
	private Long idFilial = 0L;
	
	private Boolean gravar = true; 
	private String texto;
	
	
	
	public void pesquisar() {
		funcionarios = funcionarioService.listarFuncionarioPeloNomeLike(texto);
	}
	
	private void atualizarLista() {
		funcionarios = funcionarioService.listAll();
	}
	
	@PostConstruct
	public void iniciar() {
		listarFuncionarios();
		filiais = filialService.listAll();
	}
	
	public void gravar() {
		
		endereco = enderecoService.mergeEndereco(endereco); // o metodo merge foi utilizado para deixar o objeto gerenciado novamente
	    FacesContext.getCurrentInstance().addMessage("msg1", new FacesMessage("Endereco gravada com Sucesso!"));
	    
	    funcionario.setEndereco(endereco);
	    
	    Filial f = filialService.obtemPorId(idFilial);
		funcionario.setFilial(f);
		funcionarioService.create(funcionario);
	    
	    FacesContext.getCurrentInstance().addMessage("msg1", new FacesMessage("Funcionario gravada com Sucesso!"));
	    funcionario = new Funcionario();
	    endereco = new Endereco();
	    atualizarLista();
	    listarFuncionarios();
		idFilial = 0L;
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
	
	public void listarFuncionarios() {
		funcionarios = funcionarioService.listAll(); 
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Filial> getFiliais() {
		return filiais;
	}

	public void setFiliais(List<Filial> filiais) {
		this.filiais = filiais;
	}

	public Long getIdFilial() {
		return idFilial;
	}

	public void setIdFilial(Long idfilial) {
		this.idFilial = idfilial;
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

