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
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AdministratorWordUpdateTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	//Features comprobadas en el siguiente test: Administrator Word Update (Caso positivo)
	//El CSV contiene palabras existentes en el sample data, la palabra actual, su nueva palabra, el id y su índice en la tabla tras listar.
	//El test se dirige al listado de las palabras,
	// pulsa sobre una palabra (método show), coincidiendo con la palabra correspondiente en el CSV 
	//(comprobando que coincide el valor de la palabra antigua y el id)
	//En el formulario se comprueba que la palabra coincide con la correspondiente, y se cambia a una nueva palabra
	//Finalmente se espera que en el listado el valor de la palabra correspondiente tenga el nuevo valor, y que mantenga el mismo id 

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/word/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void updatePositive(final Integer recordIndex, final Integer id, final String oldWord, final String newWord) {
		this.resetDataBase();
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam Module");	
		super.clickAndWait(By.xpath("//*[@id='form']/button[3]")); //Edit Spam Words button
		super.checkSimplePath("/administrator/word/list");
		
		super.checkEntityIdFromRowHasValue(recordIndex, id);
		super.checkColumnHasValue(recordIndex, 0, oldWord);
		
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("value", oldWord);
		
		super.fillInputBoxIn("value", newWord);
		super.clickOnSubmitButton("Update");
		
		super.checkEntityIdFromRowHasValue(recordIndex, id);
		super.checkColumnHasValue(recordIndex, 0, newWord);
	
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("value", newWord);

		super.signOut();
	}
	
	
	//Features comprobadas en el siguiente test: Administrator Word Update (Caso positivo)
	//El CSV contiene palabras existentes en el sample data, la palabra actual, su nueva palabra, el id y su índice en la tabla tras listar
	//El test accede al formulario de editar una palabra a través del servicio UPDATE (/administrator/word/update?id=%s), 
	//al contrario que el test anterior, que se accede a través del servcio SHOW.
	// Se comprueba que la palabra del formulario coincide con la palabra antigua correspondiente en el CSV  se cambia a una nueva palabra
	//Finalmente se espera que al volver al formulario de edición de la palabra (usando su mismo id en la URL), aparezca la nueva palabra

	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/word/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void updatePositive2(final Integer recordIndex, final Integer id, final String oldWord, final String newWord) {		
		super.resetDataBase();
		super.signIn("administrator", "administrator");
		
		final String updateWordSimplePath=String.format("/administrator/word/update?id=%s", id);
		final String urlUpdateWord= super.getBaseUrl() + updateWordSimplePath;
		this.driver.get(urlUpdateWord);
		
		super.checkInputBoxHasValue("value", oldWord);
		
		super.fillInputBoxIn("value", newWord);
		super.clickOnSubmitButton("Update");
		
		this.driver.get(urlUpdateWord);
		super.checkInputBoxHasValue("value", newWord);

		super.signOut();
	}
	
	
	
	
	
	//Features comprobadas en el siguiente test: Administrator Word Update (Caso negativo)
		//El CSV contiene palabras existentes en el sample data, la palabra actual, su nueva palabra, el id y su índice en la tabla tras listar.
		//El test se dirige al listado de las palabras, pulsa sobre una palabra (método show), coincidiendo con la palabra correspondiente en el CSV 
		//(comprobando que coincide el valor de la palabra antigua y el id)
		//En el formulario se comprueba que la palabra coincide con la correspondiente, y se cambia a una nueva palabra inválida
		//Finalmente se espera que se produzcan errores de binding en el formulario de edición
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/word/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)	
	public void updateNegative1(final Integer recordIndex, final Integer id, final String oldWord, final String newWord) {
		this.resetDataBase();
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam Module");	
		super.clickAndWait(By.xpath("//*[@id='form']/button[3]")); //Edit Spam Words button
		super.checkSimplePath("/administrator/word/list");
		
		super.checkEntityIdFromRowHasValue(recordIndex, id);
		super.checkColumnHasValue(recordIndex, 0, oldWord);
		
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("value", oldWord);
		
		super.fillInputBoxIn("value", newWord);
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
	
	//Features comprobadas en el siguiente test: Administrator Word Update (Caso negativo)
	//El CSV contiene identificadores de palabras que no existen. 
	//Se espera que cuando el administrator intente actualizar una de estas "palabras inexistentes" se produzca un error de panic
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/word/update-negative2.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(40)	
	public void updateNegative2(final Integer id) {		
		
		super.signIn("administrator", "administrator");
		
		final String updateWordSimplePath=String.format("/administrator/word/update?id=%s",id);
		final String urlUpdateWord= super.getBaseUrl() + updateWordSimplePath;
		this.driver.get(urlUpdateWord);
		
		super.checkPanicExists();

		super.signOut();
	}
	//El CSV contiene identificadores de palabras que existen. Se espera que cuando el rol de MANAGER intente editar alguna de estas palabras
	//salte un error de panic, puesto que este rol no está autorizado para realizar esta acción
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/word/update-negative3.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(50)	
	public void updateNegative3(final Integer id) {		
		super.resetDataBase();
		super.signIn("manager1", "manager1");
		
		final String updateWordSimplePath=String.format("/administrator/word/update?id=%s",id);
		final String urlUpdateWord= super.getBaseUrl() + updateWordSimplePath;
		this.driver.get(urlUpdateWord);
		
		super.checkPanicExists();

		super.signOut();
	}
	
	//El CSV contiene identificadores de palabras que existen. Se espera que cuando el rol de ANONYMOUS intente editar alguna de estas palabras
	//salte un error de panic, puesto que este rol no está autorizado para realizar esta acción
		
		@ParameterizedTest
		@CsvFileSource(resources = "/administrator/word/update-negative3.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(60)	
		public void updateNegative4(final Integer id) {		
			
			final String updateWordSimplePath=String.format("/administrator/word/update?id=%s",id);
			final String urlUpdateWord= super.getBaseUrl() + updateWordSimplePath;
			this.driver.get(urlUpdateWord);
			
			super.checkPanicExists();

			
		}
	// Ancillary methods ------------------------------------------------------


}
