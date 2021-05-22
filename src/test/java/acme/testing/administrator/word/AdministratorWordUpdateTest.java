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
	//El CSV contiene por cada palabra existente en el sample data, la palabra actual, su nueva palabra, el id y el índice en la tabla
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
		super.checkInputBoxHasValue("word", oldWord);
		
		super.fillInputBoxIn("word", newWord);
		super.clickOnSubmitButton("Update");
		
		super.checkEntityIdFromRowHasValue(recordIndex, id);
		super.checkColumnHasValue(recordIndex, 0, newWord);
	
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("word", newWord);

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/word/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void updatePositive2(final Integer recordIndex, final Integer id, final String oldWord, final String newWord) {		
		super.resetDataBase();
		super.signIn("administrator", "administrator");
		
		final String deleteWordSimplePath=String.format("/administrator/word/update?id=%s", id);
		final String urlUpdateWord= super.getBaseUrl() + deleteWordSimplePath;
		this.driver.get(urlUpdateWord);
		
		super.checkInputBoxHasValue("word", oldWord);
		
		super.fillInputBoxIn("word", newWord);
		super.clickOnSubmitButton("Update");
		
		this.driver.get(urlUpdateWord);
		super.checkInputBoxHasValue("word", newWord);

		super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------


}
