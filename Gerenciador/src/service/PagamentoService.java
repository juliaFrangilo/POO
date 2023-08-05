package service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import modelo.Pagamento;

@Stateless
public class PagamentoService extends GenericService<Pagamento>{

	public PagamentoService() {
		super(Pagamento.class);
	}

	@PersistenceContext(unitName="punit")
    private EntityManager entityManager;
	
	
	public Pagamento mergePagamento(Pagamento pagamento){
		return getEntityManager().merge(pagamento);			
	}	
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}


