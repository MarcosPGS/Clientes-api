package br.com.marcos.clientes.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class LocalDateConverter {
	
	public LocalDate converter(String value) {
		if (value == null) {
			return null;
		}
		LocalDate data = LocalDate.parse(value, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		return data;
	}

}
