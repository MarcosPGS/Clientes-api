package br.com.marcos.clientes.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marcos.clientes.exceptions.RegraException;
import br.com.marcos.clientes.model.Cliente;
import br.com.marcos.clientes.repository.ClienteRepository;
import br.com.marcos.clientes.util.ValidarCPF;

@Service
public class ClienteService {
	@Autowired 
	private ClienteRepository repository;
	
	
	
	public List<Cliente> listarTodosCliente(){
		return repository.findAll();
	}
	
	
	public List<Cliente> buscarClientePorNome(String nome){
		return repository.findByNome(nome);
	}
	
	public Cliente salvarCliente(Cliente c) throws RegraException {
		Cliente cliente = new Cliente();
		cliente.setCpf(c.getCpf());
		cliente.setNome(c.getNome().toUpperCase());
		
		if (ValidarCPF.isValidCpf(cliente.getCpf()) == false ) {
			throw new RegraException("Cpf inválido.");
		}
		
		Cliente clienteEncontrado = repository.findByCpf(cliente.getCpf());
		
		if (clienteEncontrado != null) {
			throw new RegraException("CPF já foi cadastrado!");
		}
		
		Cliente clienteSalvo = repository.save(cliente);
		
		return clienteSalvo;
		
	}
	
	public Cliente atualizarCliente(Cliente cliente) {
		Objects.requireNonNull(cliente.getId());
		Cliente clienteEncontrado = obterClientePorCpf(cliente.getCpf());
		clienteEncontrado.setNome(cliente.getNome());
			
		clienteEncontrado.setDataAtualizacao(LocalDate.now());
			return repository.save(clienteEncontrado);
	}
	
	public Cliente obterClientePorCpf(String cpf) {
		return repository.findByCpf(cpf);
	}

	public Optional<Cliente> obterClientePorId(Long id) {
		return repository.findById(id);
	}
	
	
	
	public String deletarCliente(Long id) {
		if (id != null) {
			repository.deleteById(id);
			return "Cliente excluido com sucesso!";
		}
		return "Erro ao excluido cliente!";
	}
	


}
