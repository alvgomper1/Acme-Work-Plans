
package acme.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import acme.framework.testing.AbstractTest;

public class ManagerCreateTest extends AbstractTest {

	@Override
	@BeforeAll
	public void beforeAll() {
		super.beforeAll();

		super.setBaseCamp("http", "localhost", "8090", "/Acme-Planner", "/master/welcome", "?language=en&debug=true");
		super.setAutoPausing(true);

		this.signIn("administrator", "administrator");
		super.click(By.linkText("Administrator"));
		super.submit(By.linkText("Populate DB (initial)"));
		this.signOut();
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

		final String name = "Alvaro";
		final String password = "StrongPassword_1";
		final String username = "alvgomperManager";
		final String surname = "Gomez";
		final String email = "alvManager@gmail.com";
		this.signUp(username, password, name, surname, email);
		this.signIn(username, password);
		assert super.exists(By.linkText("Account"));

		super.click(By.linkText("Account"));
		assert super.exists(By.linkText("Become a manager"));

		super.click(By.linkText("Become a manager"));
		super.click(By.xpath("//*[@id=\"form\"]/button[1]"));
		assert super.exists(By.linkText("Manager"));

		assert !super.exists(By.linkText("Become a manager"));
		this.signOut();
	}

	/**
	 * La feature que prueba este test es la de convertirseen manager, pero el caso negativo, que seria intentando convertir a
	 * un autenticado en manager cuando ya se habia convertido previamente
	 * <p>
	 * Lo primero es comprobar que no existe el link de "convertirse en manager". Despues intentamos acceder mediante url
	 * al formulario de "convertirse en manager", y comprobamos que el resultado es una pagina de error, ya que no está autorizado.
	 */

	@Test
	public void convertirEnManagerNegativo() {

		final String password = "StrongPassword_1";
		final String username = "alvgomperManager";

		this.signIn(username, password);
		assert super.exists(By.linkText("Manager"));
		assert !super.exists(By.linkText("Become a manager"));
		super.driver.get("http://localhost:8090/Acme-Planner/authenticated/manager/create");
		Assertions.assertEquals("Unexpected error", super.driver.findElement(By.xpath("/html/body/div[2]/div/h1")).getText());

	}

	protected void signIn(final String username, final String password) {
		super.navigateHome();
		super.click(By.linkText("Sign in"));
		super.fill(By.id("username"), username);
		super.fill(By.id("password"), password);
		super.click(By.id("remember$proxy"));
		super.submit(By.className("btn-primary"));
	}

	protected void signOut() {
		super.navigateHome();
		super.submit(By.linkText("Sign out"));
	}

	protected void signUp(final String username, final String password, final String name, final String surname, final String email) {
		super.navigateHome();
		super.click(By.linkText("Sign up"));
		super.fill(By.id("username"), username);
		super.fill(By.id("password"), password);
		super.fill(By.id("confirmation"), password);
		super.fill(By.id("identity.name"), name);
		super.fill(By.id("identity.surname"), surname);
		super.fill(By.id("identity.email"), email);
		super.click(By.id("accept$proxy"));
		super.submit(By.className("btn-primary"));
	}

}
