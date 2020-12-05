package br.com.marcos.clientes.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ClienteInfoDTO {
	private long quantidadeClientesAtivos;
	private long quantidadeClientesInativos;
	private long totalClientes;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	
	
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public long getQuantidadeClientesAtivos() {
		return quantidadeClientesAtivos;
	}
	public void setQuantidadeClientesAtivos(long quantidadeClientesAtivos) {
		this.quantidadeClientesAtivos = quantidadeClientesAtivos;
	}
	public long getQuantidadeClientesInativos() {
		return quantidadeClientesInativos;
	}
	public void setQuantidadeClientesInativos(long quantidadeClientesInativos) {
		this.quantidadeClientesInativos = quantidadeClientesInativos;
	}
	public long getTotalClientes() {
		return totalClientes;
	}
	public void setTotalClientes(long totalClientes) {
		this.totalClientes = totalClientes;
	}
	
	
	
	

}
