package com.search.mutant.domain.response;

/**
 * Contiene la respuesta que vera el usuario al llamar /stats
 * @author Caldera_Suhjail
 *
 */
public class StatsResponse {
	
	private Long count_mutant_dna;
	private Long count_human_dna;
	private Double ratio;
	
	public StatsResponse(Long count_mutant_dna, Long count_human_dna, Double ratio) {
		super();
		this.count_mutant_dna = count_mutant_dna;
		this.count_human_dna = count_human_dna;
		this.ratio = ratio;
	}

	public Long getCount_mutant_dna() {
		return count_mutant_dna;
	}

	public void setCount_mutant_dna(Long count_mutant_dna) {
		this.count_mutant_dna = count_mutant_dna;
	}

	public Long getCount_human_dna() {
		return count_human_dna;
	}

	public void setCount_human_dna(Long count_human_dna) {
		this.count_human_dna = count_human_dna;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}
	
		
}
