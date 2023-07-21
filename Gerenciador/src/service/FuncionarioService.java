package service;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import modelo.Funcionario;




@Stateless
public class FuncionarioService extends GenericService<Funcionario>{
	
	public FuncionarioService() {
		super(Funcionario.class);
	}
	
	@PersistenceContext(unitName="punit")
    private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	public Funcionario mergeFuncionario(Funcionario funcionario){
		return getEntityManager().merge(funcionario);			
	}	
	public List<Funcionario> ordernaNomeFuncionario() {
		final CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Funcionario> cQuery = cBuilder.createQuery(Funcionario.class);
		final Root<Funcionario> rootFuncionario = cQuery.from(Funcionario.class);

		cQuery.select(rootFuncionario);
		cQuery.orderBy(cBuilder.asc(rootFuncionario.<String>get("nome")));
		
		
		List<Funcionario> ordenado = 
				getEntityManager().createQuery(cQuery).getResultList();
		return ordenado;
	}

	public List<Funcionario> listarFuncionarioPeloNomeLike(String nome){
		final CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Funcionario> cQuery = cBuilder.createQuery(Funcionario.class);
		final Root<Funcionario> rootFuncionario = cQuery.from(Funcionario.class);
		
		final Expression<String> expNome = rootFuncionario.get("nome");
		
		cQuery.select(rootFuncionario);
		cQuery.where(cBuilder.like(expNome, "%"+nome+"%"));
		cQuery.orderBy(cBuilder.asc(expNome));
		
		
		List<Funcionario> resultado = 
				getEntityManager().createQuery(cQuery).getResultList();
		
		return resultado;	
	}
	
	public Long listarTotalFuncionarioPorFilial(Long idFilial){
		final CriteriaBuilder cBuilder = 
				getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Long> cQuery = 
				cBuilder.createQuery(Long.class);
		final Root<Funcionario> rootFuncionario = cQuery.from(Funcionario.class);
		
		cQuery.select(cBuilder.count(rootFuncionario));
		cQuery.where(cBuilder.equal(rootFuncionario.get("filial").get("id"),idFilial));
		
		Long resultado = 
				getEntityManager().createQuery(cQuery).getSingleResult();
		
		return resultado;
		
	}
	
	public List<Funcionario> listarFuncionarioValorSalarialSemFilial(Double salarioInicial, Double salarioFinal){
		final CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Funcionario> cQuery = cBuilder.createQuery(Funcionario.class);
		final Root<Funcionario> rootFuncionario = cQuery.from(Funcionario.class);
		
		final Expression<String> expSalario = rootFuncionario.get("salario");
		
		cQuery.select(rootFuncionario);
		cQuery.where(cBuilder.between(rootFuncionario.<Double>get("salario"),salarioInicial,salarioFinal));
		cQuery.orderBy(cBuilder.desc(expSalario));
		
		
		List<Funcionario> resultado = 
				getEntityManager().createQuery(cQuery).getResultList();
		
		return resultado;	
	}
	
	
	public List<Funcionario> listarFuncionarioValorSalarialComFilial(Double salarioInicial, Double salarioFinal, Long idFilial){
		final CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Funcionario> cQuery = cBuilder.createQuery(Funcionario.class);
		final Root<Funcionario> rootFuncionario = cQuery.from(Funcionario.class);
		
		final Expression<String> expSalario = rootFuncionario.get("salario");
		
		cQuery.select(rootFuncionario);
		cQuery.where(cBuilder.and(cBuilder.equal(rootFuncionario.get("filial").get("id"),idFilial), cBuilder.between(rootFuncionario.<Double>get("salario"),salarioInicial,salarioFinal)));
		cQuery.orderBy(cBuilder.desc(expSalario));
		
		
		List<Funcionario> resultado = 
				getEntityManager().createQuery(cQuery).getResultList();
		
		return resultado;	
	}
	
	public List<Funcionario> listarFuncionarioPorFilial(Long idFilial){
		final CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Funcionario> cQuery = cBuilder.createQuery(Funcionario.class);
		final Root<Funcionario> rootFuncionario = cQuery.from(Funcionario.class);
		
		final Expression<String> expNome = rootFuncionario.get("nome");
		
		cQuery.select(rootFuncionario);
		cQuery.where(cBuilder.equal(rootFuncionario.get("filial").get("id"),idFilial));
		cQuery.orderBy(cBuilder.asc(expNome));
		
		
		List<Funcionario> resultado = 
				getEntityManager().createQuery(cQuery).getResultList();
		
		return resultado;	
	}
	
	public List<Funcionario> listarFuncionarioPorFilialOrdemSalario(Long idFilial){
		final CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Funcionario> cQuery = cBuilder.createQuery(Funcionario.class);
		final Root<Funcionario> rootFuncionario = cQuery.from(Funcionario.class);
		
		final Expression<String> expSalario = rootFuncionario.get("salario");
		
		cQuery.select(rootFuncionario);
		if ( idFilial != 0L ) {
			cQuery.where(cBuilder.equal(rootFuncionario.get("filial").get("id"),idFilial));
		}
		
		cQuery.orderBy(cBuilder.desc(expSalario));
		
		
		List<Funcionario> resultado = 
				getEntityManager().createQuery(cQuery).getResultList();
		
		return resultado;	
	}
	
}
