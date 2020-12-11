package br.com.marcos.clientes.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GerarSenha {
	public static void main(String[] args) {
		BCryptPasswordEncoder gerador = new BCryptPasswordEncoder();
			System.out.println(gerador.encode("12345678"));
		}
}
