
package acme.testing.administrator.spam;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AdministratorUpdateThresholdTest extends AcmePlannerTest {

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

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spam/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updateTresholdPositive(final String t) {

		super.signIn("administrator", "administrator");
		 
		super.clickOnMenu("Administrator", "Spam Module");
		super.clear(By.id("threshold"));
		super.fill(By.id("threshold"), t);
		super.clickAndGo(By.xpath("//*[@id=\"form\"]/button[1]"));

		super.clickOnMenu("Administrator", "Spam Module");
		 

		Assertions.assertEquals(t, super.locateOne(By.id("threshold")).getAttribute("value"));
		this.signOut();

	}

	/**
	 * La feature que prueba este test es la de actualizar el treshold como administrador, esta vez, el caso negativo
	 *  en el que se introducen valores de trehold incorrectos, es decir, valores nulos, negativos, mayores a 100, con letras y
	 *  con mas decimales de la ceunta<p>
	 * Una vez iniciada sesion como administrador, se accede a su menu desplegable y se entra
	 * al modulo de spam, donde se establece el nuevo valor que se le quiere probar. Se van a probar una serie de valores
	 * que no pueden ponerse como valor del treshold debido a una restriccion de los requisitos. Se envia con el boton de
	 * "Update" y se comprueba que se nos devuelve un error de formulario para todos los intentos.
	 */

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spam/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void updateTresholdNegative(final String t) {

		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Spam Module");

		super.clear(By.id("threshold"));
		super.fill(By.id("threshold"), t);
		super.clickAndGo(By.xpath("//*[@id=\"form\"]/button[1]"));

		// Comprobacion de error del formulario tras pulsar el boton de actualizar el treshold
		this.checkErrorsExist();

		this.signOut();

	}

	/**
	 * La feature que prueba este test es la de actualizar el treshold como administrador, esta vez, el caso negativo
	 * intentado actualizarlo siendo manager o anonymous
	 * <p>
	 * Una vez iniciada sesion como manager,se accede mediante url al modulo de spam
	 * La comprobacion que se hace es ver si se nos ha devuelto el mensaje de error.
	 */

	@Test
	public void notAuthorisedUpdateSpam() {

		super.navigateHome();
		super.signIn("manager1", "manager1");

		super.driver.get("http://localhost:8090/Acme-Planner/administrator/spam/update");
		this.checkPanicExists();
		this.signOut();
		this.navigateHome();
		super.driver.get("http://localhost:8090/Acme-Planner/administrator/spam/update");
		this.checkPanicExists();
		
	}

}
