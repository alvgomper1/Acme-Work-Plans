
package acme.testing.administrator.spam;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AdministratorUpdateTresholdTest extends AcmePlannerTest {

	@Override
	@BeforeAll
	public void beforeAll() {
		super.beforeAll();

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

		super.signIn("administrator", "administrator");
		final String t = "30.00";
		super.clickAndGo(By.linkText("Administrator"));
		super.clickAndGo(By.linkText("Spam Module"));

		super.clear(By.id("threshold"));
		super.fill(By.id("threshold"), t);
		super.clickAndGo(By.xpath("//*[@id=\"form\"]/button[1]"));

		super.clickAndGo(By.linkText("Administrator"));
		super.clickAndGo(By.linkText("Spam Module"));

		Assertions.assertEquals(t, super.locateOne(By.id("threshold")).getAttribute("value"));
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

		super.signIn("administrator", "administrator");

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
			super.clickAndGo(By.linkText("Administrator"));
			super.clickAndGo(By.linkText("Spam Module"));

			super.clear(By.id("threshold"));
			super.fill(By.id("threshold"), t.get(i));
			super.clickAndGo(By.xpath("//*[@id=\"form\"]/button[1]"));

			// Comprobacion de error del formulario tras pulsar el boton de actualizar el treshold
			this.checkErrorsExist();

		
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
		super.signIn("manager1", "manager1");

		super.driver.get("http://localhost:8090/Acme-Planner/administrator/spam/update");
		Assertions.assertEquals("Unexpected error", super.driver.findElement(By.xpath("/html/body/div[2]/div/h1")).getText());

	}

}
