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

package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class SignUpTest extends AcmeWorkPlansTest {

	
	/**
	 * La feature que prueba este test es la de registrar un nuevo usuario
	 * <p>
	 * Se prueba que las acciones signUp, signIn y signOut se realicen correctamente usando los datos de pruebas del csv que se indica en la ruta de la anotacion
	 * @CsvFileResource
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/sign-up/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveSignUp(final String username, final String password, final String name, final String surname, final String email, final String accept) {
		super.navigateHome();
		super.clickOnLink("Sign up");
		
		super.fillInputBoxIn("username", username);
		super.fillInputBoxIn("password", password);
		super.fillInputBoxIn("confirmation", password);
		super.fillInputBoxIn("identity.name", name);
		super.fillInputBoxIn("identity.surname", surname);
		super.fillInputBoxIn("identity.email", email);
		super.fillInputBoxIn("accept", accept);
		

		super.clickOnSubmitButton("Sign up");
		super.signIn(username, password);
		super.signOut();
	}
	
	/**
	 * La feature que prueba este test es la de registrar un nuevo usuario y que el formulario de error por que el campo username está vacío
	 * <p>
	 * Navegamos por el home y clicamos en el boton sign up, aqui se abriria el formulario de registro y se rellenarian los campos con los datos obtenidos desde el csv
	 * que se indica en la ruta de la anotación @CsvFileSource. Pero en este caso al campo username le pasaremos la cadena vacia "" provocando así un error en el formulario
	 * ya que según el validador ningún campo debe estar vacío.
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/sign-up/negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeUsernameSignUp(final String username, final String password, final String name, final String surname, final String email, final String accept) {

		super.navigateHome();
		super.clickOnLink("Sign up");
		
		super.fillInputBoxIn("username", username);
		super.fillInputBoxIn("password", password);
		super.fillInputBoxIn("confirmation", password);
		super.fillInputBoxIn("identity.name", name);
		super.fillInputBoxIn("identity.surname", surname);
		super.fillInputBoxIn("identity.email", email);
		super.fillInputBoxIn("accept", accept);
		

		
		super.clickOnSubmitButton("Sign up");
		super.checkErrorsExist();
		
		
	}
	
	/**
	 * La feature que prueba este test es la de registrar un nuevo usuario y que el formulario de error por que la contraseña y la confirmación de contraseña no coincidan
	 * <p>
	 * Navegamos por el home y clicamos en el boton sign up, aqui se abriria el formulario de registro y se rellenarian los campos con los datos obtenidos desde el csv
	 * que se indica en la ruta de la anotación @CsvFileSource. Pero en este caso al campo confirmation le pasaremo una cadena distinta a la que obtenemos del csv provocando así un fallo en el formulario
	 * ya que según el validador los campos password y confirmation deben ser iguales.
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/sign-up/negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativePasswordNotMatchSignUp(final String username, final String password, final String name, final String surname, final String email, final String accept) {

		super.navigateHome();
		super.clickOnLink("Sign up");
		
		super.fillInputBoxIn("username", username);
		super.fillInputBoxIn("password", password);
		super.fillInputBoxIn("confirmation", password+"s");
		super.fillInputBoxIn("identity.name", name);
		super.fillInputBoxIn("identity.surname", surname);
		super.fillInputBoxIn("identity.email", email);
		super.fillInputBoxIn("accept", accept);

		
		super.clickOnSubmitButton("Sign up");
		super.checkErrorsExist();
		
	}
	
	/**
	 * La feature que prueba este test es la de registrar un nuevo usuario y que el formulario de error por que todos los campos esten vacíos.
	 * <p>
	 * Navegamos por el home y clicamos en el boton sign up, aqui se abriria el formulario de registro y se rellenarian los campos con los datos obtenidos desde el csv
	 * que se indica en la ruta de la anotación @CsvFileSource. Pero en este caso a todos los campos le pasaremos la cadena vacia "" provocando así un error en el formulario
	 * ya que según el validador ningún campo debe estar vacío.
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/sign-up/negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeAllEmptySignUp(final String username, final String password, final String name, final String surname, final String email, final String accept) {

		super.navigateHome();
		super.clickOnLink("Sign up");
		
		super.fillInputBoxIn("username", username);
		super.fillInputBoxIn("password", password);
		super.fillInputBoxIn("confirmation", password);
		super.fillInputBoxIn("identity.name", name);
		super.fillInputBoxIn("identity.surname", surname);
		super.fillInputBoxIn("identity.email", email);
		super.fillInputBoxIn("accept", accept);

		
		super.clickOnSubmitButton("Sign up");
		super.checkErrorsExist();
		
	}
	
	/**
	 * La feature que prueba este test es la de registrar un nuevo usuario y que el formulario de error por que no se acepte la licencia
	 * <p>
	 * Navegamos por el home y clicamos en el boton sign up, aqui se abriria el formulario de registro y se rellenarian los campos con los datos obtenidos desde el csv
	 * que se indica en la ruta de la anotación @CsvFileSource. Pero en este caso aunque todos los campos estén bien cubiertos, como no se ha clicado en aceptar la licencia 
	 * el formulario da un error.
	 * 
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/sign-up/negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeCheckboxEmptySignUp(final String username, final String password, final String name, final String surname, final String email, final String accept) {

		super.navigateHome();
		super.clickOnLink("Sign up");
		
		super.fillInputBoxIn("username", username);
		super.fillInputBoxIn("password", password);
		super.fillInputBoxIn("confirmation", password);
		super.fillInputBoxIn("identity.name", name);
		super.fillInputBoxIn("identity.surname", surname);
		super.fillInputBoxIn("identity.email", email);
		super.fillInputBoxIn("accept", accept);

		
		super.clickOnSubmitButton("Sign up");
		super.checkErrorsExist();
		
	}
	
	/**
	 * La feature que prueba este test es la de registrar un nuevo usuario y que el formulario de error por que el formato del email no es el adecuado
	 * <p>
	 * Navegamos por el home y clicamos en el boton sign up, aqui se abriria el formulario de registro y se rellenarian los campos con los datos obtenidos desde el csv
	 * que se indica en la ruta de la anotación @CsvFileSource. En este caso el formato del email no es válido por lo que el formulario da un error.
	 * 
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/sign-up/negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeWrongEmailFormatSignUp(final String username, final String password, final String name, final String surname, final String email, final String accept) {

		super.navigateHome();
		super.clickOnLink("Sign up");
		
		super.fillInputBoxIn("username", username);
		super.fillInputBoxIn("password", password);
		super.fillInputBoxIn("confirmation", password);
		super.fillInputBoxIn("identity.name", name);
		super.fillInputBoxIn("identity.surname", surname);
		super.fillInputBoxIn("identity.email", email);
		super.fillInputBoxIn("accept", accept);

		
		super.clickOnSubmitButton("Sign up");
		super.checkErrorsExist();
		
	}
	
}