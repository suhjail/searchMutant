package com.search.mutant.domain;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.search.mutant.exceptions.MutantException;
import com.search.mutant.exceptions.RequestDNAException;

@Component
public class SearchMutant {

	static final Logger LOGGER = LoggerFactory.getLogger(SearchMutant.class);
	
	public boolean isMutant(String[] dna) throws RequestDNAException {
		return (isValidArray(dna) && isValidMatrice(dna));
	}
	
	/**
	 * Valida que el arreglo ingresado sea valido
	 * @param dna: arreglo a validar
	 * @return true: valido
	 * @throws RequestDNAException
	 */
	public  boolean isValidArray(String[] dna) throws RequestDNAException {
		if(isNotValidLengthMatrice(dna) || !isValidADN(dna)) {
			LOGGER.error("Longitud o secuencia de ADN invalida");	
			throw new RequestDNAException("Request no valido");
		}
		return true;
	}
	
	/**
	 * Valida que el arreglo y su contenido tenga la longitud para validar un DNA mutante
	 * @param dna : arreglo a validar
	 * @return true: longitud no valido; false: longitud valida
	 */
	public  boolean isNotValidLengthMatrice(String[] dna){
		return ((dna.length < 4 && dna[0].length() < 2) || 
				(dna[0].length() < 4 && dna.length < 2));
	}
	
	/**
	 * Valida si el string contiene adn mutante
	 * @param stringValidation: string a validar
	 * @return true: comtiene ADN mutante; false: no contiene
	 */
	public  boolean containADN(String stringValidation){
		List<String> list = Arrays.asList("AAAA", "CCCC", "TTTT", "GGGG");
		return list.stream().anyMatch(stringValidation::contains);
	}
	
	/**
	 * Valida que el contenido del ADN tenga unicamente letras mutantes (ACTG)
	 * @param dna: arreglo a validar
	 * @return true: adn valido
	 */
	public  boolean isValidADN(String[] dna){
		Pattern p = Pattern.compile("^[A,C,T,G]+$");	
		for(int pos = 0; pos < dna.length; pos++) {
			if( (dna[pos].length() != dna[0].length()) ||
					!Arrays.asList(dna[pos]).stream()
					.filter(p.asPredicate())
					.findAny().isPresent()){	
				LOGGER.error("Secuencia de ADN invalida: " + dna[pos]);			
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Crea la matriz que se utilizara para validar al mutante de forma
	 * horizontal, vertical, oblicua izquierda y derecha
	 * @param dna: arreglo a validar
	 */
	public  char[][] createMatrice(String[] dna){
		char[][] dnaMatrix = new char[dna.length][dna[0].length()];
		for(int pos=0; pos < dna.length; pos++){
			dnaMatrix[pos]=	dna[pos].toCharArray();
		}
		return dnaMatrix;
	}
	
	/**
	 * Valida si la matriz contiene adn mutante
	 * @param dna: arreglo a validar 
	 * @return true: es mutante; false: no es mutante
	 * @throws MutantException 
	 */
	public  boolean isValidMatrice(String[] dna){
		char[][] dnaMatrix = createMatrice(dna);
		return validateHorizontal(dnaMatrix);
	}
	
	/**
	 * Valida los adn de forma horizontal
	 * @param dnaMatrix: matriz a validar
	 * @return true: es mutante; false: no esmutante
	 */
	public  boolean validateHorizontal(char[][] dnaMatrix){
		int countADN = 0;
		StringBuilder stringValidation = new StringBuilder();
		for (int i = 0; i < dnaMatrix.length && countADN <= 1; i++){
			for (int j = 0; j < dnaMatrix[0].length && countADN <= 1; j++) {
				stringValidation.append(Character.toString(dnaMatrix[i][j])); 
				if(containADN(stringValidation.toString())){
					countADN++;
					LOGGER.info("Secuencia de ADN mutante horizontal encontrada: " + stringValidation);
					stringValidation = new StringBuilder();
				}
		    }
			stringValidation = new StringBuilder();
		}
		
		return countADN == 2 ? true: validateVertical(dnaMatrix, countADN); 
	}
	
	/**
	 * Valida los adn de forma vertical
	 * @param dnaMatrix: matriz a validar
	 * @param countADN: cantidad de ADN mutante encontrado
	 * @return true: es mutante; false: no esmutante
	 */
	public  boolean validateVertical(char[][] dnaMatrix, int countADN){
		StringBuilder stringValidation = new StringBuilder();
		for (int i = 0; i < dnaMatrix[0].length && countADN <= 1; i++){
			for (int j = 0; j < dnaMatrix.length && countADN <= 1; j++) {
				stringValidation.append(Character.toString(dnaMatrix[j][i])); 
				if(containADN(stringValidation.toString())){
					countADN++;
					LOGGER.info("Secuencia de ADN mutante vertical encontrada: " + stringValidation);
					stringValidation = new StringBuilder();
				}
		    }
			stringValidation = new StringBuilder();
		}
		return countADN == 2 ? true: validateObliqueLeft(dnaMatrix, countADN);
	}
	
	/**
	 * Valida los adn de forma oblicua izquierda
	 * @param dnaMatrix: matriz a validar
	 * @param countADN: cantidad de ADN mutante encontrado
	 * @return true: es mutante; false: no esmutante
	 */
	public  boolean validateObliqueLeft(char[][] dnaMatrix, int countADN){
		StringBuilder stringValidation = new StringBuilder();
		for (int i = 0; i < dnaMatrix[0].length  && countADN <= 1; i++){
			for (int j = 0; j < dnaMatrix.length && countADN <= 1 && i < dnaMatrix[0].length; j++) {
				stringValidation.append(Character.toString(dnaMatrix[j][i++])); 
				if(containADN(stringValidation.toString())){
					countADN++;
					LOGGER.info("Secuencia de ADN mutante oblicuo left encontrada: " + stringValidation);
					stringValidation = new StringBuilder();
				}
		    }
		}
		return countADN == 2 ? true: validateObliqueRight(dnaMatrix, countADN);
	}
	
	/**
	 * Valida los adn de forma oblicua derecha
	 * @param dnaMatrix: matriz a validar
	 * @param countADN: cantidad de ADN mutante encontrado
	 * @return true: es mutante; false: no esmutante
	 */
	public  boolean validateObliqueRight(char[][] dnaMatrix, int countADN){
		StringBuilder stringValidation = new StringBuilder();
		for (int i = dnaMatrix[0].length-1; i >= 0 && countADN <= 1; i--){
			for (int j = 0; j < dnaMatrix.length && countADN <= 1  && i >= 0; j++) {
				stringValidation.append(Character.toString(dnaMatrix[j][i--])); 
				if(containADN(stringValidation.toString())){
					countADN++;
					LOGGER.info("Secuencia de ADN mutante oblicuo right encontrada: " + stringValidation);
					stringValidation = new StringBuilder();
				}
		    }
		}
		return countADN == 2 ? true: false;
	}

}
