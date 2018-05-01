package com.search.mutant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.search.mutant.domain.response.StatsResponse;
import com.search.mutant.repository.MutantRepository;
import com.search.mutant.service.StatsService;

/**
 * Clase que representa la implementacion del servicio
 * Maneja la llamada al  repositorio y verifica
 * la estaditica de mutantes
 * @author Caldera_Suhjail
 *
 */
@Service
public class StatsServiceImpl implements StatsService {

	@Autowired
	private MutantRepository mutantRepository;
	
	@Override
	public StatsResponse searchStats() {		
		return calculateStats();
	}
	
	public StatsResponse calculateStats() {
		Long mutant = mutantRepository.countByIsMutant(true);
		Long human = mutantRepository.countByIsMutant(false);
		double ratio = human != 0 ? mutant/human : 0;
		StatsResponse stats = new StatsResponse(mutant, human, ratio);
		return stats;
	}
	
	
}
