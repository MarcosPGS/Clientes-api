package br.com.marcos.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.marcos.clientes.model.ServicoPrestado;

public interface ServicosPrestadosRepository extends JpaRepository<ServicoPrestado, Long>{

}
