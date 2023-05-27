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
import service.EnderecoService;
import service.FilialService;
import service.FuncionarioService;

@ViewScoped
@ManagedBean
public class FilialBean {
	
    @EJB
	private FilialService filialService;
    
    @EJB
	private EnderecoService enderecoService;

    @EJB
	private FuncionarioService funcionarioService;
	
	private Filial filial = new Filial();
	private Endereco endereco = new Endereco();
	private List<Filial> filiais = new ArrayList<Filial>();
	
	
	private Boolean gravar = true; 
	private String texto;
	private Long totalFuncionarios;

	
	public Long getTotalFuncionarios() {
		return totalFuncionarios;
	}

	public void setTotalFuncionarios(Long totalFuncionarios) {
		this.totalFuncionarios = totalFuncionarios;
	}

	private void atualizarLista() {
		filiais = filialService.listAll();
	}
	
	@PostConstruct
	private void inicializar() {
		atualizarLista();
	}
	
	public void gravar() {
		
		endereco = enderecoService.mergeEndereco(endereco); 
	    filial.setEndereco(endereco);
		filialService.create(filial);
	    
	    FacesContext.getCurrentInstance().
	    addMessage("msg1", new FacesMessage("Filial gravada com Sucesso!"));
	    filial = new Filial();
	    endereco = new Endereco();
	    atualizarLista();
	    gravar = true;
	}
	
	public void atualizar() {
		endereco = enderecoService.mergeEndereco(endereco);
		
		filial.setEndereco(endereco);
		
		filialService.merge(filial);
		FacesContext.getCurrentInstance().
		addMessage("msg1", new FacesMessage("Filial atualizada com Sucesso!"));
		filial= new Filial();
		endereco = new Endereco();
		atualizarLista();
		gravar = true;
	}
	
	public void carregarFilial(Filial f) {
		filial = f;
		endereco = f.getEndereco();	
		gravar = false;
	}

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public List<Filial> getFiliais() {
		return filiais;
	}

	public void setFiliais(List<Filial> filiais) {
		this.filiais = filiais;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
}