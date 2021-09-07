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

	
	/**
	 * La feature que prueba este test es la de actualizar un usuario desde el rol Administrator
	 * <p>
	 * Modificamos el atributo status de un Manage, este es el unico rol actualizable.
	 * @CsvFileResource
	 */
	

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/userAccount/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void positiveUpdate(final String recordIndex,final String username, final String name, final String surname, final String email, final String roles,  final String status) {
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "User accounts");

		super.clickOnListingRecord(2);
		super.fillInputBoxIn("newStatus", status);
		super.clickOnSubmitButton("Update");
	

	}
	
	/**
	 * En esta prueba se testea que salta un error si intentamos acceder a editar un Manager directamente a la URL, sin estar registrado como Administrator
	 * 
	 */
	
	@Test
	@Order(20)
	public void negativeUpdate() {

	
 		super.driver.get("http://localhost:8090/Acme-Work-Plans/administrator/user-account/show?id=7");
	 	this.checkPanicExists();
	

	}
	
	/**
	 * Aquí se prube que salta un error cuando intentamos actualizar un manager desde el rol Administrator con valores que no pasen las restricciones de
	 * el número de caracteres o de campo vacío establecidas en el servicio
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/userAccount/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeEmptyName(final String recordIndex,final String username, final String name, final String surname, final String email, final String roles,  final String status) {

	
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "User accounts");

		super.clickOnListingRecord(2);
		super.fillInputBoxIn("newStatus",status);
		super.fillInputBoxIn("identity.name",name);
		super.fillInputBoxIn("identity.surname",surname);
		super.clickOnSubmitButton("Update");
		super.checkErrorsExist();
	

	}

}
	