package com.search.mutant.service;

import com.search.mutant.exceptions.MutantException;
import com.search.mutant.exceptions.RequestDNAException;

public interface MutantService {
	
	/**
	 * Valida si existen mutantes en el arreglo ingresado y guarda en la bd
	 * los adn
	 * @param dna: arreglo con la secuencia de ADN
	 */
	public void validateADNMutant(String[] dna) throws MutantException, RequestDNAException;
	
}
