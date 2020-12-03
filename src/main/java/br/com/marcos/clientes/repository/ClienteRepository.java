package br.com.marcos.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.marcos.clientes.model.Cliente;

public interface ClienteRepository  extends JpaRepository<Cliente, Long>, ClienteRepositoryQuery{

}
