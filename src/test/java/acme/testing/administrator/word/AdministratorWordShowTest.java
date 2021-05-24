/*
 * EmployerApplicationUpdateTest.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing.administrator.word;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorWordShowTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	//Features comprobadas en el siguiente test: Administrator Word Show (Caso positivo)
	//El CSV contiene palabras existentes en el sample data, junto con el id.
	//El test prueba a introducir la URL correspondiente al método show de una palabra en concreto mediante el id
	//(Nótese que el show mediante el listado es probado en muchos de los otros tests, por ejemplo, AdministratorWordUpdateTest, por lo que
	//en este no será probado)
	
	//Se espera que la palabra que aparezca en el formulario coincida con la del CSV correspondiente.

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/word/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void showPositive(final Integer id, final String word) {
	
		super.signIn("administrator", "administrator");
	
		final String showWordSimplePath=String.format("/administrator/word/show?id=%s", id);
		final String urlShowWord= super.getBaseUrl() + showWordSimplePath;
		this.driver.get(urlShowWord);
		
		super.checkInputBoxHasValue("value", word);

		super.signOut();
	}
	
	

	
	//Features comprobadas en el siguiente test: Administrator Word Show (Caso negativo)
	//El CSV contiene ids de palabras inexistentes. Se espera cuando el rol de administrador intente visualizar palabras que no existan
	//salte un error de panic
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/word/show-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)	
	public void showNegative1(final Integer wordId) {
		super.signIn("administrator", "administrator");
		
		final String showWordSimplePath=String.format("/administrator/word/show?id=%s", wordId);
		final String urlShowWord= super.getBaseUrl() + showWordSimplePath;
		this.driver.get(urlShowWord);
		
		super.checkPanicExists();

		super.signOut();
	}
	
	//Features comprobadas en el siguiente test: Administrator Word Show (Caso negativo)
	//En el CSV están palabras que existen en el sample data.
	//Se espera que cuando un rol de "anonymous" intente visualizar una word, salte un error de panic, puesto que no está autorizado
	//para realizar esta acción
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/word/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)	
	public void showNegativeAnonymous( final Integer id, final String word) {
		
		
		final String showWordSimplePath=String.format("/administrator/word/show?id=%s", id);
		final String urlShowWord= super.getBaseUrl() + showWordSimplePath;
		this.driver.get(urlShowWord);
		
		super.checkPanicExists();
	}
	
	//Features comprobadas en el siguiente test: Administrator Word Show (Caso negativo)
		//En el CSV están palabras que existen en el sample data.
		//Se espera que cuando un rol de "manager" intente visualizar una word, salte un error de panic, puesto que no está autorizado
		//para realizar esta acción
		
		@ParameterizedTest
		@CsvFileSource(resources = "/administrator/word/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(30)	
		public void showNegativeManager( final Integer id, final String word) {
			
			super.signIn("manager1", "manager1");
			final String showWordSimplePath=String.format("/administrator/word/show?id=%s", id);
			final String urlShowWord= super.getBaseUrl() + showWordSimplePath;
			this.driver.get(urlShowWord);
			
			super.checkPanicExists();
			super.signOut();
		}
	
	// Ancillary methods ------------------------------------------------------


}
