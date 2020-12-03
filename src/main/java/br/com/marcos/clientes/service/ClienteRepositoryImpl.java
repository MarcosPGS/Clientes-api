package br.com.marcos.clientes.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import br.com.marcos.clientes.model.Cliente;
import br.com.marcos.clientes.repository.ClienteRepositoryQuery;

public class ClienteRepositoryImpl implements ClienteRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Cliente findByCpf(String cpf) {
		 Cliente clienteEncontrado = null;
		 
		 try {
			 CriteriaBuilder builder = manager.getCriteriaBuilder();
			 CriteriaQuery<Cliente> clienteCr = builder.createQuery(Cliente.class);
			 
			 Root<Cliente> clienteRoot = clienteCr.from(Cliente.class);
			 
			 Predicate[] predicates = criarResticoes(cpf, builder, clienteRoot);
			 clienteCr.where(predicates);
			 
			 TypedQuery<Cliente> query = manager.createQuery(clienteCr);

			 clienteEncontrado = query.getSingleResult();

	           return clienteEncontrado;
			
		} catch (Exception e) {
			return clienteEncontrado;
		}
	}

	private Predicate[] criarResticoes(String cpf, CriteriaBuilder builder, Root<Cliente> clienteRoot) {
		 List<Predicate> predicates = new ArrayList<>();
	        if(!StringUtils.isEmpty(cpf)){
	            predicates.add(builder.equal(clienteRoot.get("cpf"),cpf));

	        }
	        return predicates.toArray(new Predicate[predicates.size()]);
	}

	

}
