/*
 * SignUpTest.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing.administrator.userAccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorUpdateUserTest extends AcmePlannerTest {

	

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/userAccount/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveUpdate(final String recordIndex,final String username, final String name, final String surname, final String email, final String roles,  final String status) {
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "User accounts");

		super.clickOnListingRecord(2);
		super.fillInputBoxIn("newStatus", status);
		super.clickOnSubmitButton("Update");
		

	}
	
	@Test
	@Order(20)
	public void negativeUpdate() {

	
 		super.driver.get("http://localhost:8090/Acme-Planner/administrator/user-account/show?id=7");
	 	this.checkPanicExists();
	

	}

}
	