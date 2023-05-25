package service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import modelo.Endereco;

@Stateless
public class EnderecoService extends GenericService<Endereco> {
	public EnderecoService() {
		super(Endereco.class);
	}
	
	@PersistenceContext(unitName="punit")
    private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	public Endereco mergeEndereco(Endereco endereco){
		return getEntityManager().merge(endereco);			
	}
}
