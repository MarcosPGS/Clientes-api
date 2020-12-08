package br.com.marcos.clientes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.marcos.clientes.model.ServicoPrestado;

public interface ServicosPrestadosRepository extends JpaRepository<ServicoPrestado, Long>{

	@Query("select s from ServicoPrestado s join s.cliente c "
			+ "where  upper( c.nome ) like upper( :nome ) and MONTH( s.data ) =:mes")
	public List<ServicoPrestado> findByClienteAndMes(@Param("nome") String nome, @Param("mes") Integer mes);
}
