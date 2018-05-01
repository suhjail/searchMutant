package com.search.mutant.service;

import com.search.mutant.domain.response.StatsResponse;

public interface StatsService {
	
	/**
	 * Devuelve la estadisticas de mutantes
	 * @return estadisca de mutantes
	 */
	public StatsResponse searchStats();

}
