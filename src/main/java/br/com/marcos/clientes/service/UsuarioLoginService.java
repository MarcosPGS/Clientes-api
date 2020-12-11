package br.com.marcos.clientes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marcos.clientes.entity.Usuario;
import br.com.marcos.clientes.exceptions.RegraException;
import br.com.marcos.clientes.repository.UsuarioRepository;

@Service
public class UsuarioLoginService {

	@Autowired
	private UsuarioRepository repository;
	
	public String salvarUsuario(Usuario usuario) throws RegraException {
		Usuario usuarioEncontrado = buscarUsuarioPorNome(usuario.getUsername());
		if (usuarioEncontrado != null) {
			throw new RegraException("Já existe usuário com esse nome.");
		}
		repository.save(usuario);
		return "Usuário criado com sucesso";
	}
	
	public Usuario buscarUsuarioPorNome(String nome){
		return repository.findByNome(nome);
	}
	
	public Optional<Usuario> findUsuarioPorNome(String nome){
		return repository.findByUsername(nome);
	}
}
