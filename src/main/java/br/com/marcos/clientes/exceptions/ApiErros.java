package br.com.marcos.clientes.exceptions;

import java.util.Arrays;
import java.util.List;

public class ApiErros {

	private List<String> errors;
	

	public ApiErros(List<String> errors) {
		super();
		this.errors = errors;
	}
	

	public ApiErros(String message) {
		this.errors = Arrays.asList(message);
	}


	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	
}
