
package acme.testing;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import acme.framework.testing.AbstractTest;

public class AdministratorUpdateTresholdTest extends AbstractTest {

	@Override
	@BeforeAll
	public void beforeAll() {
		super.beforeAll();

		super.setBaseCamp("http", "localhost", "8090", "/Acme-Planner", "/master/welcome", "?language=en&debug=true");
		super.setAutoPausing(false);

		this.signIn("administrator", "administrator");
		super.click(By.linkText("Administrator"));
		super.submit(By.linkText("Populate DB (initial)"));
		this.navigateHome();
	}
	/**
	 * La feature que prueba este test es la de actualizar el treshold como administrador
	 * <p>
	 * Una vez iniciada sesion como administrador, se accede a su menu desplegable y se entra
	 * al modulo de spam, donde se establece el nuevo valor que se le quiere probar. Se envia con el boton de "Update"
	 * y se comprueba entrando de nuevo al modulo que el valor ha quedado registrado correctamente.
	 */
	@Test
	public void actualizarTresholdPositivo() {

		final String t = "30.00";
		super.click(By.linkText("Administrator"));
		super.submit(By.linkText("Spam Module"));

		super.clear(By.id("threshold"));
		super.fill(By.id("threshold"), t);
		super.click(By.xpath("//*[@id=\"form\"]/button[1]"));

		super.click(By.linkText("Administrator"));
		super.submit(By.linkText("Spam Module"));

		Assertions.assertEquals(t, super.locate(By.id("threshold")).getAttribute("value"));
		this.signOut();

	}

	/**
	 * La feature que prueba este test es la de actualizar el treshold como administrador, esta vez, el caso negativo
	 * <p>
	 * Una vez iniciada sesion como administrador, se accede a su menu desplegable y se entra
	 * al modulo de spam, donde se establece el nuevo valor que se le quiere probar. Se van a probar una serie de valores
	 * que no pueden ponerse como valor del treshold como restriccion de los requisitos. Se envia con el boton de
	 * "Update" y se comprueba que se nos devuelve un error de formulario para todos los intentos.
	 */

	@Test
	public void actualizarTresholdNegativo() {

		this.signIn("administrator", "administrator");

		//Lista de valores para introducir en el formulario
		final List<String> t = new ArrayList<String>();

		t.add("30.005");
		t.add("300.005");
		t.add("200.00");
		t.add("-50.00");
		t.add("-50.004");
		t.add("3Esta mal");
		t.add("");

		for (int i = 0; i < 7; i++) {
			super.click(By.linkText("Administrator"));
			super.submit(By.linkText("Spam Module"));

			super.clear(By.id("threshold"));
			super.fill(By.id("threshold"), t.get(i));
			super.click(By.xpath("//*[@id=\"form\"]/button[1]"));

			// Respuestas de error del formulario tras pulsar el boton de actualizar el treshold
			Assertions.assertTrue(
				super.locate(By.className("text-danger")).getText().equals("Must have 3 digits and 2 decimals.") || super.locate(By.className("text-danger")).getText().equals("Must have 3 digits and 2 decimals. Must be less than or equal to 100.")
					|| super.locate(By.className("text-danger")).getText().equals("Must have 3 digits and 2 decimals. Must be greater than or equal to 0.") || super.locate(By.className("text-danger")).getText().equals("Invalid value.")
					|| super.locate(By.className("text-danger")).getText().equals("Must be less than or equal to 100.") || super.locate(By.className("text-danger")).getText().equals("Must be greater than or equal to 0.")
					|| super.locate(By.className("text-danger")).getText().equals("Must be greater than or equal to 0. Must have 3 digits and 2 decimals.")
					|| super.locate(By.className("text-danger")).getText().equals("Must be less than or equal to 100. Must have 3 digits and 2 decimals."));

		}
		this.signOut();

	}

	/**
	 * La feature que prueba este test es la de actualizar el treshold como administrador, esta vez, el caso negativo
	 * intentado actualizarlo siendo manager
	 * <p>
	 * Una vez iniciada sesion como manager,se accede mediante url al modulo de spam
	 * La comprobacion que se hace es ver si se nos ha devuelto el mensaje de error.
	 */

	@Test
	public void accesoNoAutorizadoActualizarSpam() {

		super.navigateHome();
		this.signIn("manager1", "manager1");

		super.driver.get("http://localhost:8090/Acme-Planner/administrator/spam/update");
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

}
