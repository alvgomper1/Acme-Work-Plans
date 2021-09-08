/*
 * EmployerJobCreateTest.java
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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmeWorkPlansTest;

public class AdministratorWordCreateTest extends AcmeWorkPlansTest {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	
	/* In this test the following feature will be tested: Administrator Word Create and Administrator Word Show (Positive test)*/
	/* All words in CSV have a length between 0 and 30 characters and no words are empty, so all the words can be created.
	 * The new words will appear in the list, and when we click on one, its details will be displayed correctly.*/
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/word/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String word) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Spam Module");	
		super.clickAndWait(By.xpath("//*[@id='form']/button[3]")); //List words button
		super.clickAndWait(By.xpath("//*[@id='form']/button")); //Create word button

		super.checkSimplePath("/administrator/word/create");
		super.fillInputBoxIn("value", word);
		super.clickOnSubmitButton("Create");
		//super.checkNotErrorsExist();
		
		super.clickOnMenu("Administrator", "Spam Module");	
		super.clickAndWait(By.xpath("//*[@id='form']/button[3]")); //List words button
		super.checkColumnHasValue(recordIndex, 0, word);
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("value", word);
		
		super.signOut();
	}
	
	
	/* In this test the following feature will be tested: Administrator Word Create  (Negative test)*/
	/* Some words in CSV haven´t a length between 0 and 30 characters and someone are empty, so all the words can´t be created.
	 * Binding errors are expecting*/
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/word/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final int recordIndex,final String word) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Spam Module");	
		super.clickAndWait(By.xpath("//*[@id='form']/button[3]")); //List words button
		super.clickAndWait(By.xpath("//*[@id='form']/button")); //Create word button
		super.checkSimplePath("/administrator/word/create");
		super.fillInputBoxIn("value", word);
		super.clickOnSubmitButton("Create");

		super.checkErrorsExist();

		super.signOut();
	}
	
	/* In this test the following feature will be tested: Administrator Word Create  (Negative test)*/
	/* Anonymous role will try to create a word. A panic error is expecting, because Anonymous rol cannot create a word */
	
	@Test
	@Order(30)
	public void AnonymousTriesToCreateWords() {

 		super.driver.get("http://localhost:8090/Acme-Work-Plans/administrator/word/create");
		super.checkPanicExists();
		

	}
	
	/* In this test the following feature will be tested: Administrator Word Create  (Negative test)*/
	/* Manager role will try to create a word. A panic error is expecting, because Manager rol cannot create a word */
	
	@Test
	@Order(40)
	public void ManagerTriesToCreateWords() {
		super.signIn("manager1", "manager1");
 		super.driver.get("http://localhost:8090/Acme-Work-Plans/administrator/word/create");
		super.checkPanicExists();
		super.signOut();
	
	}


	// Ancillary methods ------------------------------------------------------
	
}
