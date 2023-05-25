package service;

import java.util.List;

import javax.ejb.Stateless;
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
	
	
	public List<Funcionario> listarFuncionarioPeloNomeLike(String nome){
		final CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Funcionario> cQuery = cBuilder.createQuery(Funcionario.class);
		final Root<Funcionario> rootFuncionario = cQuery.from(Funcionario.class);
		
		final Expression<String> expNome = rootFuncionario.get("nome");
		
		cQuery.select(rootFuncionario);
		cQuery.where(cBuilder.like(expNome, "%"+nome+"%"));
		cQuery.orderBy(cBuilder.asc(expNome));
		
		
		List<Funcionario> resultado = getEntityManager().createQuery(cQuery).getResultList();
		
		return resultado;
		
	}
}
