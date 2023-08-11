package service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;
import modelo.Funcionario;
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
	
	public boolean verificaDuplicidade(int mesReferente, int anoReferente, long idfuncionario) {
	    final CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
	    final CriteriaQuery<Long> cQuery = cBuilder.createQuery(Long.class);
	    final Root<Pagamento> rootPagamento = cQuery.from(Pagamento.class);

	    cQuery.select(cBuilder.count(rootPagamento));
	    cQuery.where(cBuilder.and(cBuilder.equal(rootPagamento.get("mesReferente"), mesReferente),
	                              cBuilder.equal(rootPagamento.get("anoReferente"), anoReferente),
	                              cBuilder.equal(rootPagamento.get("funcionario"), idfuncionario)));

	        Long count = 
	    		getEntityManager().createQuery(cQuery).getSingleResult();

	    return count > 0;
	}

	 
	public List<Pagamento> obterPagamentosPorFuncionarioOrdenados(Funcionario funcionario) {
	    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Pagamento> criteriaQuery = criteriaBuilder.createQuery(Pagamento.class);
	    Root<Pagamento> pagamentoRoot = criteriaQuery.from(Pagamento.class);
	    
	    Join<Pagamento, Funcionario> funcionarioJoin = pagamentoRoot.join("funcionario");
	    criteriaQuery.select(pagamentoRoot);
	    criteriaQuery.where(criteriaBuilder.equal(funcionarioJoin.get("id"), funcionario.getId()));
	    criteriaQuery.orderBy(
	        criteriaBuilder.asc(pagamentoRoot.get("anoReferente")),
	        criteriaBuilder.asc(pagamentoRoot.get("mesReferente"))
	    );
	    
	    return entityManager.createQuery(criteriaQuery).getResultList();
	}
	
	
	public List<Funcionario> obterFuncionariosPagosNoPeriodo(int mes, int ano) {
	    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Funcionario> criteriaQuery = criteriaBuilder.createQuery(Funcionario.class);
	    Root<Pagamento> pagamentoRoot = criteriaQuery.from(Pagamento.class);

	    Join<Pagamento, Funcionario> funcionarioJoin = pagamentoRoot.join("funcionario");

	    criteriaQuery.select(funcionarioJoin);
	    criteriaQuery.where(
	        criteriaBuilder.and(
	            criteriaBuilder.equal(criteriaBuilder.function("month", Integer.class, pagamentoRoot.get("dataPagamento")), mes),
	            criteriaBuilder.equal(criteriaBuilder.function("year", Integer.class, pagamentoRoot.get("dataPagamento")), ano)
	        )
	    );
	    criteriaQuery.groupBy(funcionarioJoin);

	    return entityManager.createQuery(criteriaQuery).getResultList();
	}




}


