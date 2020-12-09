package br.com.marcos.clientes.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ServicoPrestadoDTO {
	
	@NotEmpty(message = "campo.descricao.obrigatorio")
	private String descricao;
	
	@NotEmpty(message = "campo.preco.obrigatorio")
	private String preco;
	
	@NotEmpty(message = "campo.data.obrigatorio")
	private String data;
	
	@NotNull(message = "campo.idCliente.obrigatorio")
	private Long idCliente;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	

}
