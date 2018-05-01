package com.search.mutant.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.search.mutant.domain.request.MutantRequest;
import com.search.mutant.domain.response.StatsResponse;
import com.search.mutant.exceptions.MutantException;
import com.search.mutant.exceptions.RequestDNAException;
import com.search.mutant.service.MutantService;
import com.search.mutant.service.StatsService;

/**
 * Recibe las llamadas de los servicios y representa
 * cl controlados de mutantes
 * @author Caldera_Suhjail
 *
 */
@RestController
@RequestMapping
public class MutantController {
	
	@Autowired
	MutantService mutantService;
	
	@Autowired
	StatsService statsService;

	@PostMapping("/mutant/")
	public void searchMutant(@Valid @RequestBody MutantRequest mutantRequest) throws MutantException, RequestDNAException{
		mutantService.validateADNMutant(mutantRequest.getDna());
	}
	
	@GetMapping("/stats")
	public StatsResponse searchStats() {
		return statsService.searchStats();
	}
	
}
