package br.com.marcos.clientes.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcos.clientes.dto.UsuarioDTO;
import br.com.marcos.clientes.entity.Usuario;
import br.com.marcos.clientes.exceptions.RegraException;
import br.com.marcos.clientes.service.UsuarioLoginService;

@RestController
@RequestMapping(value = "v1/api/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioLoginService service;
	
	
	@GetMapping("/{username}")
	public ResponseEntity<?> buscaNome(@PathVariable("username") String username) {
		
			
			try {
				Usuario usuarioEncontrado = service.buscarUsuarioPorNome(username);	
				if (usuarioEncontrado == null) {
					return ResponseEntity.ok().body(null);
				}
				
				return ResponseEntity.ok().body(usuarioEncontrado);
				
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			}
		

	}
//	
//	@GetMapping("/login/{username}")
//	public ResponseEntity<?> findUsername(@PathVariable("username") String username) {
//		
//			
//			try {
//				Optional<Usuario> usuarioEncontrado = service.findUsuarioPorNome(username);
//				if (usuarioEncontrado.get() == null) {
//					return ResponseEntity.ok().body(null);
//				}
//				
//				return ResponseEntity.ok().body(usuarioEncontrado.get());
//				
//			} catch (Exception e) {
//				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//			}
//		
//
//	}
//	
	@PostMapping
	public ResponseEntity<Object> salvarUsuario(@RequestBody UsuarioDTO usuario){
		try {
			service.salvarUsuario(usuario);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (RegraException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} 
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}