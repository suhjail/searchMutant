package com.search.mutant.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Excepcion que devuelve el codigo 403 forbidden
 * cuando el dna ingresado no pertenece a un mutante
 * @author Caldera_Suhjail
 *
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class MutantException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public MutantException(String message) {
	      super(message);
	}
	
}
