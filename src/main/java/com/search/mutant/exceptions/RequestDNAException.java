package com.search.mutant.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepcion que devuelve codigo 400 cuando el request ingresado no
 * es valido
 * @author Caldera_Suhjail
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequestDNAException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public RequestDNAException(String message) {
	      super(message);
	}

}
