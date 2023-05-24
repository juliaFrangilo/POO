package controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Filial;
import service.FilialService;

@ViewScoped
@ManagedBean
public class FilialBean {
	
	@EJB
	private FilialService filialService;
	
	private Filial filial = new Filial();
	private List<Filial> pacientes = new ArrayList<Filial>();
	
	public void gravar() {
		filialService.create(filial);
		FacesContext.getCurrentInstance().
			addMessage("msg1", new FacesMessage("Paciente gravado com Sucesso!"));
		filial = new Filial();
	}
}