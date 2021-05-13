package acme.testing;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import acme.framework.testing.AbstractTest;

public class AuthenticatedTaskListTest extends AbstractTest {

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
		super.click(By.linkText("Administrator"));
		super.submit(By.linkText("Populate DB (samples)"));
		this.signOut();

	}

	/**
	 * La feature que prueba este test es la de listar tareas siendo un usuario authenticated, es decir, autenticado
	 * <p>
	 * Este metodo accede al menu desplegable de authenticated y entra al listado
	 * tasks. Debe comprobar que el listado se muestra y no está vacio, para ello,
	 * se comprueba que en la tabla hay filas con la etiqueta
	 * 'td' y la clase "control.sorting_1" o que no existe la clase "dataTables_empty", ya que esto significa que
	 * no hay tasks disponibles en la lista.
	 *  * Tambien se hace la comprobacion de que la variable visibility y finished de cada task cumple con la restriccion de mostrar
	 * solo las tasks con visibilidad "PUBLIC" y cuyo valor finished sea "true"
	 */

	@Test
	public void listTasksAuthenticated() {

		//Accedemos al listado de tasks de authenticated
		super.navigateHome();
		this.signIn("administrator", "administrator");
		super.click(By.linkText("Authenticated"));
		super.submit(By.linkText("List tasks ended"));

		//Se comprueba que se muestra el listado con o sin datos
		Assertions.assertTrue(super.exists(By.cssSelector(".control.sorting_1")) || super.exists(By.className("dataTables_empty")));
		
		final List<WebElement> elements = super.driver.findElements(By.cssSelector(".control.sorting_1"));
		
		for (int i = 1; i <= elements.size(); i++) {
			final String publicTask = super.driver.findElement(By.xpath("//*[@id=\"list\"]/tbody/tr[" + i + "]/td[6]")).getText();
			final String finishedTask = super.driver.findElement(By.xpath("//*[@id=\"list\"]/tbody/tr[" + i + "]/td[7]")).getText();
		

			//Comprobacion de la restriccion de la variable visibility y finished
			Assertions.assertEquals("PUBLIC" , publicTask);
			Assertions.assertEquals("true", finishedTask);
		}
	}

	/**
	 * La feature que prueba este test es la de listar tasks como authenticated, pero el caso negativo, que seria intentando acceder al listado con un usuario no logueado, 
	 * es decir, como anonymous.
	 * <p>
	 * Una vez accedemos al menu principal como anonymous, lo primero es comprobar que no existe la seccion de authenticated. Despues intentamos acceder mediante url
	 * al listado de tasks", y comprobamos que el resultado es una pagina de error, ya que no está autorizado.
	 */

	@Test
	public void listTasksAuthenticatedNegative() {

		super.navigateHome();
		assert !super.exists(By.linkText("Authenticated"));
		super.driver.get("http://localhost:8090/Acme-Planner/authenticated/task/list");
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
