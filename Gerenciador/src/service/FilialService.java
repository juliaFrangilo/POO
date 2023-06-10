package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import modelo.Filial;

@Stateless
public class FilialService extends GenericService<Filial> {
	
	public FilialService() {
		super(Filial.class);
		
	}
	
	@PersistenceContext(unitName="punit")
    private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	
	}
	
	public Filial mergeFilial(Filial filial){
		return getEntityManager().merge(filial);			
	}	
	
	public List<Filial> ordernaNomeFilial() {
		final CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Filial> cQuery = cBuilder.createQuery(Filial.class);
		final Root<Filial> rootFilial = cQuery.from(Filial.class);

		cQuery.select(rootFilial);
		cQuery.orderBy(cBuilder.asc(rootFilial.<String>get("nome")));
		
		
		List<Filial> ordemFilial= 
				getEntityManager().createQuery(cQuery).getResultList();
		return ordemFilial;
	}
}