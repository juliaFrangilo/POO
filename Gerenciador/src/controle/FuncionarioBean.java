package controle;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Cargo;
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
	private List<Cargo> cargos;

	
	private Long idFilial;
	private String texto;
	
	
	
	public void pesquisar() { // Pesquisa por nome
		funcionarios = funcionarioService.listarFuncionarioPeloNomeLike(texto);
		texto = null;

		 if (funcionarios.isEmpty()) {
			 FacesContext.getCurrentInstance().
			    addMessage("msg1", new FacesMessage("Aviso!!! Funcion�rio n�o encontrado."));
		 }
	}
	
	private void atualizarLista() {
		funcionarios = funcionarioService.ordernaNomeFuncionario();
	}
	
	@PostConstruct
	public void iniciar() {
		atualizarLista();
		filiais = filialService.listAll();
		cargos = Arrays.asList(Cargo.values());
		
	}

	public void gravar() {
		if (funcionario.getSalario() == 0.0  ) {
			 FacesContext.getCurrentInstance().
			    addMessage("msg1", new FacesMessage("Campo sal�rio obrigat�rio."));
		 } else if(idFilial == 0){
			 FacesContext.getCurrentInstance().
			    addMessage("msg1", new FacesMessage("Selecione a filial do funcion�rio"));
		 }else if (funcionario.getCargo() == null) {
			 FacesContext.getCurrentInstance().
                addMessage("msg1", new FacesMessage("Selecione o cargo do funcion�rio."));
			 return;
		 }else {
			 
			 if(funcionario.getId()==null) {
			// o metodo merge foi utilizado para deixar o objeto gerenciado novamente
			    endereco = enderecoService.mergeEndereco(endereco);
			    funcionario.setEndereco(endereco);
			    	   
			    		
			   	Filial f = filialService.obtemPorId(idFilial);
			    		
			    funcionario.setFilial(f);
			    funcionario = funcionarioService.mergeFuncionario(funcionario);
			    FacesContext.getCurrentInstance().
			    addMessage("msg1", new FacesMessage("Funcionario gravado com Sucesso!"));
			    funcionario = new Funcionario();
			    endereco = new Endereco();
			    atualizarLista();
			    idFilial = 0L;
			 }else {
				// Verificar se houve tentativa de alterar a filial
				    if (!funcionario.getFilial().getId().equals(idFilial)) {
				    	FacesContext.getCurrentInstance().
						addMessage("msg1", new FacesMessage("Aviso!!! "
								+ "N�o � permitido alterar a filial do funcion�rio."));
				        return;
				    }
				    
					endereco = enderecoService.mergeEndereco(endereco);
					
					funcionarioService.merge(funcionario);
					FacesContext.getCurrentInstance().
					addMessage("msg1", new FacesMessage("Funcionario atualizado com Sucesso!"));
					funcionario = new Funcionario();
					endereco = new Endereco();
					atualizarLista();
					idFilial = 0L; 
			 }
		 }
	}
	
	
	public void carregarFuncionario(Funcionario f) {
		funcionario = f;
		idFilial = f.getFilial().getId();
		endereco = f.getEndereco();
	 }

	public void excluirFuncionario(Funcionario f) {
		funcionarioService.remove(f);
		atualizarLista();
		FacesContext.getCurrentInstance().
		addMessage("msg1", new FacesMessage("Funcionario removido com sucesso!"));
	}
	
	public static String formatarCPF(String cpf) {
	    if (cpf.length() < 9) {
	        return cpf; // Retorna o CPF sem formata��o se tiver menos de 9 caracteres
	    }

	    return MessageFormat.format("{0}.{1}.{2}-{3}",
	            cpf.substring(0, 3),
	            cpf.substring(3, 6),
	            cpf.substring(6, 9),
	            cpf.substring(9));
	}
	
	// getters e setters
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

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	
	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}
}
