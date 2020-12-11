package br.com.marcos.clientes.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcos.clientes.dto.ServicoPrestadoDTO;
import br.com.marcos.clientes.entity.ServicoPrestado;
import br.com.marcos.clientes.exceptions.RegraException;
import br.com.marcos.clientes.service.ServicosPrestadosService;

@RestController
@RequestMapping(value = "v1/api/servicos")
public class ServicosPrestadosResource {
	@Autowired
	private ServicosPrestadosService service;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_PESQUISAR') and #oauth.hasScope('read')")
	public ResponseEntity<List<ServicoPrestado>> listarServicos(){
		List<ServicoPrestado> servicos = service.listarTodosServicos();
		if (servicos == null) {
			return ResponseEntity.ok(null);
		}
		return ResponseEntity.ok(servicos);
	}
	
	@GetMapping("/pesquisar")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_PESQUISAR') and #oauth.hasScope('read')")
	public ResponseEntity<Object> listarServicosPesquisa(@RequestParam (value = "nome", required = false) String nome,
			@RequestParam (value = "mes", required = false) Integer mes) throws RegraException{
		
		try {
			List<ServicoPrestado> servicos = service.pesquisarServicos(nome, mes);
			return ResponseEntity.ok(servicos);
		} catch (RegraException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CADASTRAR') and #oauth.hasScope('write')")
	public ResponseEntity<Object> salvarServico( @RequestBody @Valid ServicoPrestadoDTO dto){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarServicoPrestado(dto));
			
		}
		catch (RegraException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
