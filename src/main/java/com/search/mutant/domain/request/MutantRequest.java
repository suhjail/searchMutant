package com.search.mutant.domain.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Contiene la entrada del dna de la llamada rest
 * @author Caldera_Suhjail
 *
 */
public class MutantRequest {

	@NotNull(message = "El atributo 'dna' es requerido.")
	@NotEmpty(message = "El atributo 'dna' es requerido.")
	private String[] dna;

	public String[] getDna() {
		return dna;
	}

	public void setDna(String[] dna) {
		this.dna = dna;
	}
	
	
}
