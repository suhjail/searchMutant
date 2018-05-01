package com.search.mutant.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.search.mutant.domain.entity.MutantEntity;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

/**
 * Representa el repositorio de mutantes y contiene la llamada
 * de los mutantes y humanos que se encuentran la bd
 * @author Caldera_Suhjail
 *
 */
@Repository
@Transactional(propagation = Propagation.NESTED)
public interface MutantRepository  extends CrudRepository<MutantEntity, Long>{
	
	/**
	 * Busca si existe el dna en BD y devuelve si es mutante
	 * @param dna: adn a consultar
	 * @return Devuelve si es mutante
	 */
	public Optional<MutantEntity> findOptionalByAdn(String[] adn);
	
	/**
	 * Devuelve la cantidad de humanos o mutantes
	 * @param isMutant: true devuelve mutante, false: devuelve humanos 
	 * @return Devuelve cantidad de mutantes/humano
	 */
	public Long countByIsMutant(boolean isMutant);
}
