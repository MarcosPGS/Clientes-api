package br.com.marcos.clientes.util;

import br.com.caelum.stella.validation.CPFValidator;

public class ValidarCPF {
	public static boolean isValidCpf(String cpf) { 
	    CPFValidator cpfValidator = new CPFValidator(); 
	    try{ cpfValidator.assertValid(cpf); 
	    return true; 
	    }catch(Exception e){ 
	        e.printStackTrace(); 
	        return false; 
	        } 
	    }

}
