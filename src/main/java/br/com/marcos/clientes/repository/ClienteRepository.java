package br.com.marcos.clientes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.marcos.clientes.entity.Cliente;

public interface ClienteRepository  extends JpaRepository<Cliente, Long>, ClienteRepositoryQuery{

	
	@Query(value = " SELECT c FROM Cliente c WHERE c.nome LIKE %:nome%")
	public List<Cliente> findByNome(@Param("nome") String nome);
}
