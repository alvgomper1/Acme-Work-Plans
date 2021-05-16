
package acme.testing.manager.create;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class ManagerCreateTest extends AcmePlannerTest {

	@Override
	@BeforeAll
	public void beforeAll() {
		super.beforeAll();

		 
 
	}
	/**
	 * La feature que prueba este test es la de convertirse en manager
	 * <p>
	 * Lo primero que se hace es registrar a un usuario nuevo y iniciar sesion para tenerlo autenticado.
	 * Este metodo accede al menu desplegable de account, entra a "convertirse en manager" y confirma que
	 * quiere convertirse pulsando el boton de "registrar".
	 * Una vez convertido en manager, se comprueba que ya existe en el menu desplegable la seccion "Manager", además de
	 * que ya no existe el link de "convertirse en manager".
	 */
	@Test
	public void convertirEnManagerPositivo() {

		
		final String password = "StrongPassword_1";
		final String username = "alvgomperManager";
		final String surname = "Gomez";
		final String email = "alvManager@gmail.com";
	 
		super.signUp(username, password, username, surname, email, email);
		super.signIn(username, password);
		assert super.exists(By.linkText("Account"));

		super.clickAndGo(By.linkText("Account"));
		assert super.exists(By.linkText("Become a manager"));

		super.clickAndGo(By.linkText("Become a manager"));
		super.clickAndGo(By.xpath("//*[@id=\"form\"]/button[1]"));
		assert super.exists(By.linkText("Manager"));

		assert !super.exists(By.linkText("Become a manager"));
		this.signOut();
	}

	/**
	 * La feature que prueba este test es la de convertirse en manager, pero el caso negativo, que seria intentando convertir a
	 * un autenticado en manager cuando ya se habia convertido previamente
	 * <p>
	 * Lo primero es comprobar que no existe el link de "convertirse en manager". Despues intentamos acceder mediante url
	 * al formulario de "convertirse en manager", y comprobamos que el resultado es una pagina de error, ya que no está autorizado.
	 */

	@Test
	public void convertirEnManagerNegativo() {

		final String password = "StrongPassword_1";
		final String username = "alvgomperManager";

		super.signIn(username, password);
		assert super.exists(By.linkText("Manager"));
		assert !super.exists(By.linkText("Become a manager"));
		super.driver.get("http://localhost:8090/Acme-Planner/authenticated/manager/create");
		Assertions.assertEquals("Unexpected error", super.driver.findElement(By.xpath("/html/body/div[2]/div/h1")).getText());

	}

 

}
