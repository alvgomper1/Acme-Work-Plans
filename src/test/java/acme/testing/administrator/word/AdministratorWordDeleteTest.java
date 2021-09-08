/*
 * EmployerJobListAllTest.java
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
import org.openqa.selenium.By;

import acme.testing.AcmeWorkPlansTest;


public class AdministratorWordDeleteTest extends AcmeWorkPlansTest {

	// Lifecycle management ---------------------------------------------------
	
	
	
	
	
	// Test cases -------------------------------------------------------------

	//Features comprobadas en el siguiente test: Administrator Word Delete (Caso positivo)
	//El CSV contiene todas las palabras de spam existentes en el sample-data. El test se dirige al listado de las palabras,
	// pulsa sobre una palabra (método show), coincidiendo con la palabra correspondiente en el CSV.
	//Posteriormente elimina esa palabra (En caso de éxito redirije el flujo al listado otra vez) 
	//y para comprobar que se ha eliminado la palabra, intentamos hacer un show de esa palabra borrada.
	//Se espera un panic en esta comprobación final, ya que hemos intentado acceder a una palabra borrada que ya no existe.
	

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/word/deletePositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deletePositive(final int recordIndex, final String word) {		

		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam Module");	
		super.clickAndWait(By.xpath("//*[@id='form']/button[3]")); //Edit Spam Words button
		super.checkSimplePath("/administrator/word/list");
		
		
		super.checkColumnHasValue(recordIndex, 0, word);
		
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("value", word);
		final String urlShowDeletedWord= this.driver.getCurrentUrl();  //IP:Port/Acme-Planner/administrator/word/show?id=X
		
		super.clickOnSubmitButton("Delete");
		super.checkSimplePath("/administrator/word/list");
		
		this.driver.get(urlShowDeletedWord);
		super.checkPanicExists();
		
		super.signOut();
		this.resetDataBase();

	}
	

		
		//Features comprobadas en el siguiente test: Administrator Word Delete (Caso positivo)
		//Esta vez intentaremos que el administrador borre las palabras sin pasar por el listado ni por el método show, 
		//sino que el formulario de borrado se generará a partir de la petición GET al servicio de DELETE
		//("administrator/delete/word?id=X") en lugar del de SHOW como en el anterior test. 
		//El CSV contiene identificadores de palabras que existen. Se espera que
		// tras borrar una palabra no haya excepción de panic, y que cuando volvamos a intentar eliminar esa palabra aparezca un error
		//de panic, puesto que se acaba de borrar. (Tarda bastante en ejecutarse el test)
		
		@ParameterizedTest
		@CsvFileSource(resources = "/administrator/word/deletePositive2.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(50)
		public void deletePositive2(final String wordId) {		
			
			super.signIn("administrator", "administrator");
			
			
			final String deleteWordSimplePath=String.format("/administrator/word/delete?id=%s", wordId);
			final String urlDeleteWord= super.getBaseUrl() + deleteWordSimplePath;
			this.driver.get(urlDeleteWord);

			super.clickOnSubmitButton("Delete");
			super.checkNotPanicExists();

			this.driver.get(urlDeleteWord);
			super.checkPanicExists();
		
			super.signOut();
			this.resetDataBase();
		}
		

		
		//Features comprobadas en el siguiente test: Administrator Word Delete (Caso negativo)
		//El administrador intentará eliminar una palabara que no exista. El CSV contiene identificadores de palabras que no existen
		//Se espera que se devuelva un panic al solicitar borrar una palabra inexistente.
		
		@ParameterizedTest
		@CsvFileSource(resources = "/administrator/word/deleteNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(20)
		public void deleteNegative(final String wordId) {		
			
			super.signIn("administrator", "administrator");
			
			
			final String deleteWordSimplePath=String.format("/administrator/word/delete?id=%s", wordId);
			final String urlDeleteWord= super.getBaseUrl() + deleteWordSimplePath;
			this.driver.get(urlDeleteWord);
			super.checkPanicExists();
		
			super.signOut();
		}
		
		//Features comprobadas en el siguiente test: Administrator Word Delete (Caso negativo)
		//Simularemos que un rol de manager intenta solicitar borrar una palabra, exista o no. El CSV contiene identificadores
		//de palabras que existen y que no existen. Se espera que se devuelva un panic ya que el rol de manager no está autorizado
		//para eliminar una palabra de spam.
		
		@ParameterizedTest
		@CsvFileSource(resources = "/administrator/word/deleteNegativeNotAuthorise.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(30)
		public void deleteNegativeManager(final String wordId) {		
			
			super.signIn("manager1", "manager1");
			
			
			final String deleteWordSimplePath=String.format("/administrator/word/delete?id=%s", wordId);
			final String urlDeleteWord= super.getBaseUrl() + deleteWordSimplePath;
			this.driver.get(urlDeleteWord);
			super.checkPanicExists();
		
			super.signOut();
		}

		//Features comprobadas en el siguiente test: Administrator Word Delete (Caso negativo)
			//Simularemos que un rol de anonymous intenta solicitar borrar una palabra, exista o no. El CSV contiene identificadores
			//de palabras que existen y que no existen. Se espera que se devuelva un panic ya que el rol de anonymous no está autorizado
			//para eliminar una palabra de spam.
			
			@ParameterizedTest
			@CsvFileSource(resources = "/administrator/word/deleteNegativeNotAuthorise.csv", encoding = "utf-8", numLinesToSkip = 1)
			@Order(40)
			public void deleteNegativeAnonymous(final String wordId) {		

				final String deleteWordSimplePath=String.format("/administrator/word/delete?id=%s", wordId);
				final String urlDeleteWord= super.getBaseUrl() + deleteWordSimplePath;
				this.driver.get(urlDeleteWord);
				super.checkPanicExists();
			
		
			}
		
		
		// Ancillary methods ------------------------------------------------------
		
		
}
