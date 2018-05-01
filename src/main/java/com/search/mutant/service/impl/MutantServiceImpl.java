package com.search.mutant.service.impl;

import java.util.Arrays;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.search.mutant.domain.SearchMutant;
import com.search.mutant.domain.entity.MutantEntity;
import com.search.mutant.exceptions.MutantException;
import com.search.mutant.exceptions.RequestDNAException;
import com.search.mutant.repository.MutantRepository;
import com.search.mutant.service.MutantService;

/**
 * Clase que representa la implementacion del servicio
 * Maneja la llamada al  repositorio y verifica que los datos del
 * ADN sean valido 
 * @author Caldera_Suhjail
 *
 */
@Service
public class MutantServiceImpl implements MutantService {	

	static final Logger LOGGER = LoggerFactory.getLogger(MutantServiceImpl.class);

	@Autowired
	private MutantRepository mutantRepository;
	
	@Autowired
	private SearchMutant searchMutant;
	
	@Override
	public void validateADNMutant(String[] dna) throws MutantException, RequestDNAException {	
		LOGGER.info("ADN a validar: " + Arrays.toString(dna));	
		Optional<MutantEntity> mutant = mutantRepository.findOptionalByAdn(dna);
		if(!mutant.isPresent()) {
			dnaMutant(dna);
		}else if(!mutant.get().isMutant) {
			LOGGER.info("El ADN existe en BD y no pertenece a un mutante");	
			throw new MutantException("ADN no mutante");			
		}	
		LOGGER.info("ADN pertenece a un mutante: " + Arrays.toString(dna));	
	}
	
	public void dnaMutant(String[] dna) throws MutantException, RequestDNAException {		
		if(searchMutant.isMutant(dna)) {
			mutantRepository.save(new MutantEntity(dna, true));
		}else {
			mutantRepository.save(new MutantEntity(dna, false));
			LOGGER.info("El adn no pertenece a un mutante");	
			throw new MutantException("ADN no mutante");
		}
	}
	
}
