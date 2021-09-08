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

package acme.testing.administrator.userAccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkPlansTest;

public class AdministratorUserAccountListTest extends AcmeWorkPlansTest {

	/**
	 * En este test se prueba el caso positivo de listar los userAccounts desde el rol de Administrator haciendonos uso de un csv que contiene los datos correctos de dicha lista.
	 * Este csv está especificado en la ruta dentro de la anotación @CsvFileSource
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/userAccount/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void listUserAccountsPositive(final int recordIndex, final String username, final String name, final String surname, final String email, final String roles, final String status) {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "User accounts");	
		
		super.checkColumnHasValue(recordIndex, 0, username);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, surname);

		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("username", username );
		super.checkInputBoxHasValue("identity.name", name);
		super.checkInputBoxHasValue("identity.surname", surname);
		super.checkInputBoxHasValue("identity.email", email );
		super.checkInputBoxHasValue("roleList", roles );
		super.checkInputBoxHasValue("status", status );

		
		super.signOut();
	}
	
	
	/**
	 * La feature que prueba este test es la de listar las userAccounts desde Administrator, pero el caso negativo, que seria intentando acceder al listado sin estar
	 * logueado como dicho rol.
	 */

	@Test
	public void listUserAccountsNegative() {

	
 		super.driver.get("http://localhost:8090/Acme-Work-Plans/administrator/user-account/list");
	 	this.checkPanicExists();
	

	}
	
	
}
