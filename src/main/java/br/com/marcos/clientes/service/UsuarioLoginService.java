package br.com.marcos.clientes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.marcos.clientes.dto.UsuarioDTO;
import br.com.marcos.clientes.entity.Usuario;
import br.com.marcos.clientes.exceptions.RegraException;
import br.com.marcos.clientes.repository.UsuarioRepository;

@Service
public class UsuarioLoginService {

	@Autowired
	private UsuarioRepository repository;
	
	public Usuario salvarUsuario(UsuarioDTO usuario) throws RegraException {
		BCryptPasswordEncoder gerador = new BCryptPasswordEncoder();
		Usuario usuarioSalvo = new Usuario();
		Usuario usuarioEncontrado = buscarUsuarioPorNome(usuario.getUsername());
		if (usuarioEncontrado != null) {
			throw new RegraException("Já existe usuário com esse nome.");
		}
		usuarioSalvo.setEmail(usuario.getEmail());
		usuarioSalvo.setNome(usuario.getNome().toUpperCase());
		usuarioSalvo.setPassword(gerador.encode(usuario.getPassword()));
		usuarioSalvo.setUsername(usuario.getUsername());
		
		return repository.save(usuarioSalvo);
	}
	
	public Usuario buscarUsuarioPorNome(String nome){
		return repository.findByNome(nome);
	}
	
	public Optional<Usuario> findUsuarioPorNome(String nome){
		return repository.findByUsername(nome);
	}
}
