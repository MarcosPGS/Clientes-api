package br.com.marcos.clientes.repository;

import br.com.marcos.clientes.model.Cliente;

public interface ClienteRepositoryQuery {
	public Cliente findByCpf(String cpf);
}
