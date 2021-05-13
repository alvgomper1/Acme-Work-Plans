package acme.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import acme.framework.testing.AbstractTest;

public class ManagerTaskListTest extends AbstractTest{

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
	 * La feature que prueba este test es la de listar tareas siendo un usuario manager, es decir, accediendo como manager
	 * <p>
	 * Este metodo accede al menu desplegable de manager y entra al listado
	 * tasks. Debe comprobar que el listado se muestra y no está vacio, para ello,
	 * se comprueba que en la tabla hay filas con la etiqueta
	 * 'td' y la clase "control.sorting_1" o que no existe la clase "dataTables_empty", ya que esto significa que
	 * no hay tasks disponibles en la lista.
	 */

	@Test
	public void listTasksManager() {

		//Accedemos al listado de tasks de manager
		super.navigateHome();
		this.signIn("manager1", "manager1");
		super.click(By.linkText("Manager"));
		super.submit(By.linkText("List tasks"));

		//Se comprueba que se muestra el listado con o sin datos
		Assertions.assertTrue(super.exists(By.cssSelector(".control.sorting_1")) || super.exists(By.className("dataTables_empty")));
	}

	/**
	 * La feature que prueba este test es la de listar tasks como manager, pero el caso negativo, que seria intentando acceder al listado con un usuario no logueado, 
	 * es decir, como anonymous.
	 * <p>
	 * Una vez accedemos al menu principal como anonymous, lo primero es comprobar que no existe la seccion de authenticated. Despues intentamos acceder mediante url
	 * al listado de tasks", y comprobamos que el resultado es una pagina de error, ya que no está autorizado.
	 */

	@Test
	public void listTasksManagerNegative() {

		super.navigateHome();
		assert !super.exists(By.linkText("Manager"));
		super.driver.get("http://localhost:8090/Acme-Planner/manager/task/list");
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
