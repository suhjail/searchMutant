package com.search.mutant.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.search.mutant.SearchMutantApplication;

/**
 * Se ejecutan los test con los distintos casos posibles en la bsuqueda de un
 * ADN mutante
 * @author Caldera_Suhjail
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = SearchMutantApplication.class)
@AutoConfigureMockMvc
public class MutantControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private static String URL_MUTANT = "/mutant/";
	private static String URL_STATS = "/stats";
	
	/**
	 * Test para el caso en que el request llega null
	 * @throws Exception
	 */
	@Test
	public void requestNullTest() throws Exception {
		this.mockMvc.perform(post(URL_MUTANT)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
	}

	/**
	 * Representa una matriz que no puede ser validada como adn mutante
	 * @throws Exception
	 */
	@Test
	public void lenthArrayInvalidTest() throws Exception {
		String dna = "{\r\n" + "\"dna\": [\"AT\",\"C\"]\r\n" + "}";
		this.mockMvc.perform(post(URL_MUTANT)
				.contentType(MediaType.APPLICATION_JSON)
				.content(dna))
		.andExpect(status().isBadRequest());
	}

	/**
	 * Test que representa un adn mutante
	 * @throws Exception 
	 */
	@Test
	public void adnMutantTest() throws Exception {
		String dna = "{\r\n" + "\"dna\": [\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]\r\n" + "}";
		this.mockMvc.perform(post(URL_MUTANT)
				.contentType(MediaType.APPLICATION_JSON)
				.content(dna))
		.andExpect(status().isOk());
		//Ingresa por el ciclo de adn existe sin necesidad de agregarlo a bd
		this.mockMvc.perform(post(URL_MUTANT)
				.contentType(MediaType.APPLICATION_JSON)
				.content(dna))
		.andExpect(status().isOk());
	}
	
	/**
	 * Test que reprenseta un adn humano
	 * @throws Exception 
	 */
	@Test
	public void adnHumanTest() throws Exception {
		String dna = "{\r\n" + "\"dna\": [\"ACAC\",\"CACA\"]\r\n" + "}";
		this.mockMvc.perform(post(URL_MUTANT)
				.contentType(MediaType.APPLICATION_JSON)
				.content(dna))
		.andExpect(status().isForbidden());
		//Ingresa por el ciclo de adn humano existente en bd
		this.mockMvc.perform(post(URL_MUTANT)
				.contentType(MediaType.APPLICATION_JSON)
				.content(dna))
		.andExpect(status().isForbidden());
	}

	/**
	 * Representa un mutante conseguido mediante secuencia oblicua 
	 * @throws Exception
	 */
	@Test
	public void adnMutantOblique() throws Exception {
		String dna = "{\r\n" + "\"dna\": [\"ATGCGA\",\"CAGTAC\",\"TTAAGT\",\"AGAATG\",\"TCCCTA\",\"TCACTG\"]\r\n" + "}";
		this.mockMvc.perform(post(URL_MUTANT)
				.contentType(MediaType.APPLICATION_JSON)
				.content(dna))
		.andExpect(status().isOk());
	}

	/**
	 * Representa la insercion de una cadena con caracter invalido
	 * @throws Exception
	 */
	@Test
	public void charAdnInvalidTest() throws Exception {
		String dna = "{\r\n" + "\"dna\": [\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACLL\"]\r\n" + "}";
		this.mockMvc.perform(post(URL_MUTANT)
				.contentType(MediaType.APPLICATION_JSON)
				.content(dna))
		.andExpect(status().isBadRequest());
	}
	
	/**
	 * Representa la llamada al servicio stats para buscar las estadisticas de mutantes y humanos
	 * @throws Exception
	 */
	@Test
	public void statsTest() throws Exception {		
		this.mockMvc.perform(get(URL_STATS)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	
}
