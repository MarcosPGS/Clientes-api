package br.com.marcos.clientes.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcos.clientes.entity.Usuario;
import br.com.marcos.clientes.exceptions.RegraException;
import br.com.marcos.clientes.service.UsuarioService;

@RestController
@RequestMapping(value = "v1/api/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService service;
	
	@PostMapping
	public ResponseEntity<?> salvarUsuario(@RequestBody Usuario usuario){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarUsuario(usuario));
		} catch (RegraException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} 
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
