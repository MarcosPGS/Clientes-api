package br.com.marcos.clientes.repository;

import java.util.List;

import br.com.marcos.clientes.model.Cliente;

public interface ClienteRepositoryQuery {
	public Cliente findByCpf(String cpf);
	public List<Cliente> findByNome(String nome);
}
