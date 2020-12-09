package br.com.marcos.clientes.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marcos.clientes.dto.ServicoPrestadoDTO;
import br.com.marcos.clientes.entity.Cliente;
import br.com.marcos.clientes.entity.ServicoPrestado;
import br.com.marcos.clientes.exceptions.RegraException;
import br.com.marcos.clientes.repository.ServicosPrestadosRepository;
import br.com.marcos.clientes.util.BigDecimalConverter;

@Service
public class ServicosPrestadosService {
	
	@Autowired
	private ServicosPrestadosRepository repository;
	
	
	@Autowired
	private BigDecimalConverter bigDecimalConverter;
	
	@Autowired
	private ClienteService clienteService;
	
	
	public List<ServicoPrestado> listarTodosServicos(){
		return repository.findAll();
	}

	public ServicoPrestado salvarServicoPrestado(ServicoPrestadoDTO dto) throws RegraException {
		ServicoPrestado servico = new ServicoPrestado();
		Optional<Cliente> clienteOptional = clienteService.bucarPorID(dto.getIdCliente());
		if (!clienteOptional.isPresent()) {
			throw new RegraException("Cliente não encontrado!");
		}
		LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		servico.setDescricao(dto.getDescricao());
		servico.setValor(bigDecimalConverter.converter(dto.getPreco()));
		servico.setData(data);
		servico.setCliente(clienteOptional.get());
		
		ServicoPrestado servicoSalvo = repository.save(servico);
		
		return servicoSalvo;
		
	}
	
	public List<ServicoPrestado> pesquisarServicos(String nome, Integer mes) throws RegraException{
		if (nome == null && mes == null) {
			throw new RegraException("Por favor digiti o nome e o mês para realizar a pesquisa.");
		}
		return repository.findByClienteAndMes("%" + nome + "%", mes);
	}
}
