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

import acme.testing.AcmePlannerTest;


public class AdministratorWordDeleteTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/word/deletePositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listAll(final int recordIndex, final String word) {		

		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam Module");	
		super.clickAndWait(By.xpath("//*[@id='form']/button[3]")); //Edit Spam Words button
		super.checkSimplePath("/administrator/word/list");
		
		super.checkColumnHasValue(recordIndex, 0, word);
		
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("word", word);
		super.clickOnSubmitButton("Delete");
		super.checkNotErrorsExist();
		super.checkSimplePath("/administrator/word/list");
		
		
		//*[@id="list"]/tbody/tr[@data-item-id="%s"] donde %s es el id
		
		
		super.signOut();

	}

	// Ancillary methods ------------------------------------------------------

}
