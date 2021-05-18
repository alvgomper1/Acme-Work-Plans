/*
 * EmployerApplicationLIstTest.java
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

import acme.testing.AcmePlannerTest;

public class AdministratorWordListTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	/* In this test the following feature will be tested: Administrator Word List and Administrator Word Show (Positive test)*/
	/* All words in CSV are the same as the one shown in the list on the page. Each word in the list will match every word in the CSV, and when we click on a word
	 * on a word, the InputField to Update it, will match with the word in the CSV too. */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/word/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void list(final int recordIndex, final String word) {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam Module");	
		super.clickAndWait(By.xpath("//*[@id='form']/button[3]")); //Edit Spam Words button
		super.checkSimplePath("/administrator/word/list");
		
		super.checkColumnHasValue(recordIndex, 0, word);
		
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("word", word);
		
		super.signOut();
	}
	
	
	/* In this test the following feature will be tested: Administrator Word List (Negative test)*/
	/* Anonymous rol tries to list spam words, but Anonymous rol is prohibited from performing this action, so a panic exception is expected*/
	
	@Test
	@Order(20)
	public void AnonymousTryToListWords() {

 		super.driver.get("http://localhost:8090/Acme-Planner/administrator/word/list");
		super.checkPanicExists();
		

	}
	

	/* In this test the following feature will be tested: Administrator Word List (Negative test)*/
	/* Manager rol tries to list spam words, but Manager rol is prohibited from performing this action, so a panic exception is expected*/
	
	@Test
	@Order(30)
	public void ManagerTryToListWords() {
		super.signIn("manager1", "manager1");
 		super.driver.get("http://localhost:8090/Acme-Planner/administrator/word/list");
		super.checkPanicExists();
		super.signOut();
		

	}
	
	
	
	// Ancillary methods ------------------------------------------------------

}
