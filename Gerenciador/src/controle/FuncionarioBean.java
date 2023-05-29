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

	
	private Long idFilial;
	private Boolean gravar = true; 
	private String texto;
	

	
	public void pesquisar() {
		funcionarios = funcionarioService.listarFuncionarioPeloNomeLike(texto);
	}

	@PostConstruct
	public void ordenaNomeFuncionario(){
		funcionarios =  funcionarioService.ordernaNomeFuncionario();
	}
	
	public Long TotalFuncionariosPorFilial(Long idFilial) {
	    return funcionarioService.listarTotalFuncionarioPorFilial(idFilial);
	}
	
	private void atualizarLista() {
		funcionarios = funcionarioService.listAll();
	}
	
	@PostConstruct
	public void iniciar() {
		atualizarLista();
		filiais = filialService.listAll();
	}

	public void gravar() {
		
		 FacesContext facesContext = FacesContext.getCurrentInstance();
	        if (facesContext.isValidationFailed()) {
	        	
	        	FacesContext.getCurrentInstance().
	    	    addMessage("msg1", new FacesMessage("Campo Nulo"));
	        } else {
		

	    		// o metodo merge foi utilizado para deixar o objeto gerenciado novamente
	    		endereco = enderecoService.mergeEndereco(endereco); 
	    	    FacesContext.getCurrentInstance().
	    	    addMessage("msg1", new FacesMessage("Endereco gravado com Sucesso!"));
	    	    funcionario.setEndereco(endereco);
	    		
	    		Filial f = filialService.obtemPorId(idFilial);
	    		
	    		funcionario.setFilial(f);
	    		funcionario = funcionarioService.mergeFuncionario(funcionario);
	    		f.setTotalFuncionarios(funcionarioService.listarTotalFuncionarioPorFilial(idFilial));
	    		System.out.println(f.getTotalFuncionarios());
	    	    FacesContext.getCurrentInstance().
	    	    addMessage("msg1", new FacesMessage("Funcionario gravado com Sucesso!"));
	    	    funcionario = new Funcionario();
	    	    endereco = new Endereco();
	    	    atualizarLista();
	    		idFilial = 0L;
	        }
	}
	
	public void atualizar() {
		
		// Verificar se houve tentativa de alterar a filial
	    if (!funcionario.getFilial().getId().equals(idFilial)) {
	    	FacesContext.getCurrentInstance().
			addMessage("msg1", new FacesMessage("Aviso!!! "
					+ "Não é permitido alterar a filial do funcionário."));
	        return;
	    }
	    
		endereco = enderecoService.mergeEndereco(endereco);
		
		funcionarioService.merge(funcionario);
		FacesContext.getCurrentInstance().
		addMessage("msg1", new FacesMessage("Funcionario atualizado com Sucesso!"));
		funcionario = new Funcionario();
		endereco = new Endereco();
		atualizarLista();
		gravar = true;
	}
	
	public void carregarFuncionario(Funcionario f) {
		funcionario = f;
		idFilial = f.getFilial().getId();
		endereco = f.getEndereco();
	    gravar = false;
	 }
	
	public void excluirFuncionario(Funcionario f) {
		funcionarioService.remove(f);
		atualizarLista();
		FacesContext.getCurrentInstance().
		addMessage("msg1", new FacesMessage("Funcionario removido com sucesso!"));
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

