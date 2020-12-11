package br.com.marcos.clientes.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.marcos.clientes.entity.Usuario;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findByUsername(String username);

	@Query(value = " SELECT u FROM Usuario u WHERE u.username =:username")
	public Usuario findByNome(@Param("username") String username);
	
}
