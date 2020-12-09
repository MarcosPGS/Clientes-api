package br.com.marcos.clientes.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.marcos.clientes.entity.Usuario;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long>{

	@Query(value = " SELECT u FROM Usuario u WHERE u.username =:username")
	public Usuario findByNome(@Param("username") String username);
}
