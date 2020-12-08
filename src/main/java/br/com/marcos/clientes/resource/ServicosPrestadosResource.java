package br.com.marcos.clientes.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcos.clientes.dto.ServicoPrestadoDTO;
import br.com.marcos.clientes.exceptions.RegraException;
import br.com.marcos.clientes.model.ServicoPrestado;
import br.com.marcos.clientes.service.ServicosPrestadosService;

@RestController
@RequestMapping(value = "v1/api/servicos")
public class ServicosPrestadosResource {
	@Autowired
	private ServicosPrestadosService service;
	
	@GetMapping
	public ResponseEntity<List<ServicoPrestado>> listarCliente(){
		List<ServicoPrestado> servicos = service.listarTodosServicos();
		if (servicos == null) {
			return ResponseEntity.ok(null);
		}
		return ResponseEntity.ok(servicos);
	}
	
	@PostMapping
	public ResponseEntity<Object> salvarServico( @RequestBody ServicoPrestadoDTO dto){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarServicoPrestado(dto));
			
		}
		catch (RegraException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}