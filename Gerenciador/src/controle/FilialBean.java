package controle;


import java.text.MessageFormat;
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

	
	
	@PostConstruct
	public void ordenaNomeFilial(){ //Ordena por nome Filial
		filiais= filialService.ordernaNomeFilial();
	}
	
	private void atualizarLista() {
		filiais = somaTotalFunc();
		filiais = filialService.ordernaNomeFilial();	
	} 
	
	@PostConstruct
	private void inicializar() {
		atualizarLista();
	}
    
	 public List<Filial> somaTotalFunc() {
		List<Filial> result = filialService.listAll();// Supondo que você obtenha uma lista de objetos Filial
		List<Filial> pronto = new ArrayList<Filial>();
	    for (Filial filial : result) {
	        Long filialId = filial.getId();
	        Long totalFuncionarios = funcionarioService.listarTotalFuncionarioPorFilial(filialId);

	        if (totalFuncionarios != null) {
	            filial.setTotalFuncionarios(totalFuncionarios);
	        } else {
	            filial.setTotalFuncionarios(0L);
	        }
	
	        pronto.add(filial);
	    }
		return pronto;
 }
	
	public void gravar() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext.isValidationFailed()) {
        	
        	FacesContext.getCurrentInstance().
    	    addMessage("msg1", new FacesMessage("Campo Nulo"));
        } else {
	
		endereco = enderecoService.mergeEndereco(endereco); 
	    filial.setEndereco(endereco);
	    
	    filial = filialService.mergeFilial(filial);
	    FacesContext.getCurrentInstance().
	    addMessage("msg1", new FacesMessage("Filial gravada com Sucesso!"));
	    filial = new Filial();
	    endereco = new Endereco();
	    atualizarLista();
	    gravar = true;
	}
}
	public void atualizar() {
		endereco = enderecoService.mergeEndereco(endereco);
		
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
	 	
	public static String formatarCNPJ(String cnpj) {
	    if (cnpj.length() < 14) {
	        return cnpj; // Retorna o CNPJ sem formatação se tiver menos de 14 caracteres
	    }

	    return MessageFormat.format("{0}.{1}.{2}/{3}-{4}",
	            cnpj.substring(0, 2),
	            cnpj.substring(2, 5),
	            cnpj.substring(5, 8),
	            cnpj.substring(8, 12),
	            cnpj.substring(12));
	}

	//getters e setters
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
	
	public FilialService getFilialService() {
		return filialService;
	}

	public void setFilialService(FilialService filialService) {
		this.filialService = filialService;
	}

	public EnderecoService getEnderecoService() {
		return enderecoService;
	}

	public void setEnderecoService(EnderecoService enderecoService) {
		this.enderecoService = enderecoService;
	}

	public FuncionarioService getFuncionarioService() {
		return funcionarioService;
	}

	public void setFuncionarioService(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}
   
	public Long getTotalFuncionarios() {
		return totalFuncionarios;
	}

	public void setTotalFuncionarios(Long totalFuncionarios) {
		this.totalFuncionarios = totalFuncionarios;
	}
		
}