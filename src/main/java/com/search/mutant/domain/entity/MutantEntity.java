package com.search.mutant.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase que representa la tabla de mutantes de la BD
 * @author Caldera_Suhjail
 *
 */
@Entity
@Table(name = "Mutant")
public class MutantEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public int id;
	
	@Column(name = "adn", nullable = false)
	public String[] adn;
	
	@Column(name = "isMutant", nullable = false)
	public boolean isMutant;
	
	public MutantEntity() {
		super();
	}

	public MutantEntity(String[] adn, boolean isMutant) {
		super();
		this.adn = adn;
		this.isMutant = isMutant;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String[] getAdn() {
		return adn;
	}

	public void setAdn(String[] adn) {
		this.adn = adn;
	}

	public boolean isMutant() {
		return isMutant;
	}

	public void setMutant(boolean isMutant) {
		this.isMutant = isMutant;
	}
	
}
