package service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import modelo.Pagamento;

@Stateless
public class PagamentoService extends GenericService<Pagamento>{

	public PagamentoService() {
		super(Pagamento.class);
	}

	@PersistenceContext(unitName="punit")
    private EntityManager entityManager;
		
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public Pagamento verificaDuplicidade(int mesReferente, int anoReferente, long idfuncionario) {
		final CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Pagamento> cQuery = cBuilder.createQuery(Pagamento.class);
		final Root<Pagamento> rootPagamento = cQuery.from(Pagamento.class);
		
		cQuery.select(rootPagamento);
		cQuery.where(cBuilder.and(cBuilder.equal(rootPagamento.get("mesReferente"), mesReferente) , cBuilder.equal(rootPagamento.get("anoReferente"), anoReferente) , cBuilder.equal(rootPagamento.get("funcionario"), idfuncionario)));
		Pagamento resultado = 
				getEntityManager().createQuery(cQuery).getSingleResult();
		
		//resultado.getAutores().size(); forçada
		
		return resultado;
	}

}


