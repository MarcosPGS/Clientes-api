package br.com.marcos.clientes.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcos.clientes.dto.ClienteInfoDTO;
import br.com.marcos.clientes.entity.Cliente;
import br.com.marcos.clientes.exceptions.RegraException;
import br.com.marcos.clientes.service.ClienteService;


@RestController
@RequestMapping(value = "v1/api/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listarCliente(){
		List<Cliente> clientes = service.listarTodosCliente();
		if (clientes == null) {
			return ResponseEntity.ok(null);
		}
		return ResponseEntity.ok(clientes);
	}
	
	// endpoint busca por nome
		@GetMapping("/{nomeCliente}")
		public ResponseEntity<?> buscaNome(@PathVariable("nomeCliente") String nome) {
			
				
				try {
					List<Cliente> clientesEncontrado = service.buscarClientePorNome(nome);		
					if (clientesEncontrado.isEmpty()) {
						return ResponseEntity.ok().body(null);
					}
					
					return ResponseEntity.ok().body(clientesEncontrado);
					
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
				}
			

		}
		
		@GetMapping("/totalizador")
		public ResponseEntity<?> totalizadoresCliente() {
			
				
				try {
					ClienteInfoDTO totalizador = service.totalizarClientes();	
					if (totalizador == null) {
						return ResponseEntity.ok().body(null);
					}
					
					return ResponseEntity.ok().body(totalizador);
					
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
				}
			

		}
		
		@GetMapping("/id/{id}")
		public ResponseEntity<?> buscaNome(@PathVariable("id") long id) {
			
				
				try {
					Optional<Cliente> clienteEncontrado = service.bucarPorID(id);		
					if (clienteEncontrado == null) {
						return ResponseEntity.ok().body(null);
					}
					
					return ResponseEntity.ok().body(clienteEncontrado);
					
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
				}
			

		}
		
		
	
	@PostMapping
	public ResponseEntity<Object> salvarCliente( @RequestBody @Valid Cliente cliente){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarCliente(cliente));
			
		} catch (RegraException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@PutMapping
	public ResponseEntity<Object> atualizar(@RequestBody @Valid Cliente cliente) {
		try {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.atualizarCliente(cliente));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar cliente.");
		}

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deletar(@PathVariable Long id) {
		return service.obterClientePorId(id).map(entidade -> {
			service.deletarCliente(entidade.getId());
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}).orElseGet(() -> new ResponseEntity("Cliente não encontrado na base de Dados.", HttpStatus.BAD_REQUEST));
	}

}
