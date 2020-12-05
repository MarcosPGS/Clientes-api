package br.com.marcos.clientes.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="nome", nullable = false, length = 150)
	@NotEmpty(message="{campo.nome.obrigatorio}")
	private String nome;
	
	@Column(name="cpf",nullable = false, length = 11)
	@NotNull
	@NotEmpty(message="{campo.cpf.obrigatorio}")
	private String cpf;
	
	@Column(name="ativo",length = 1)
	private String ativo;
	
	@Column(name="celular",length = 11)
	private String celular;
	
	@Column(name = "data_cadastro")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;
	
	@Column(name = "data_atualizacao")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAtualizacao;
	
	@PrePersist
	public void prePrersist() {
		setDataCadastro(LocalDate.now());
	}

	


	public String getCelular() {
		return celular;
	}




	public void setCelular(String celular) {
		this.celular = celular;
	}




	public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}




	public String getNome() {
		return nome;
	}




	public void setNome(String nome) {
		this.nome = nome;
	}




	public String getCpf() {
		return cpf;
	}




	public void setCpf(String cpf) {
		this.cpf = cpf;
	}




	public String getAtivo() {
		return ativo;
	}




	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}




	public LocalDate getDataCadastro() {
		return dataCadastro;
	}




	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}




	public LocalDate getDataAtualizacao() {
		return dataAtualizacao;
	}




	public void setDataAtualizacao(LocalDate dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	
}
