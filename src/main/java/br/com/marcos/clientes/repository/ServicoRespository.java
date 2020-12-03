package br.com.marcos.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.marcos.clientes.model.Servico;

public interface ServicoRespository extends JpaRepository<Servico, Long>{

}
