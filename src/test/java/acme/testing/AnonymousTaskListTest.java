package acme.testing;


import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import acme.framework.testing.AbstractTest;

public class AnonymousTaskListTest extends AbstractTest {

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
	 * La feature que prueba este test es la de listar tareas siendo un usuario anonymous, es decir, sin autenticar
	 * <p>
	 * Este metodo accede al menu desplegable de anonymous y entra al listado
	 * shouts. Debe comrobar que el listado se muestra y no está vacio, para ello,
	 * se comprueba que en la tabla hay filas con la etiqueta
	 * 'td' y la clase "control.sorting_1" o que no existe la clase "dataTables_empty", ya que esto significa que
	 * no hay shouts disponibles en la lista.
	 * Tambien se hace la comprobacion de que la variable momento del shout cumple con la restriccion de mostrar
	 * solo las mas recientes, es decir, con antiguedad maxima de 1 mes
	 */

	@Test
	public void listTasksNotEnded() {

		//Accedemos al listado de tasks de anonymous
		super.navigateHome();
		super.click(By.linkText("Anonymous"));
		super.submit(By.linkText("List tasks not ended"));

		//Se comprueba que se muestra el listado con o sin datos
		Assertions.assertTrue(super.exists(By.cssSelector(".control.sorting_1")) || super.exists(By.className("dataTables_empty")));

		//Si hay datos en el listado, recorremos el atributo finished y el atributo visibility
		if (super.exists(By.cssSelector(".control.sorting_1"))) {
			final List<WebElement> elements = super.driver.findElements(By.cssSelector(".control.sorting_1"));

			for (int i = 1; i <= elements.size(); i++) {
				final String visibility = super.driver.findElement(By.xpath("//*[@id=\"list\"]/tbody/tr["+i+"]/td[6]")).getText();
				final String finished = super.driver.findElement(By.xpath("//*[@id=\"list\"]/tbody/tr["+i+"]/td[7]")).getText();
		
				//Comprobacion de la restriccion de la variable finished
				Assertions.assertEquals("false", finished);
				//Comprobacion de la restriccion de la variable public
				Assertions.assertEquals("PUBLIC", visibility);
			}
		}

	}

	/**
	 * La feature que prueba este test es la de listar shouts como anonymous, pero el caso negativo, que seria intentando acceder al listado con un usuario logueado con
	 * el rol de Administrador
	 * <p>
	 * Una vez loguado como administrator, lo primero es comprobar que no existe la seccion de anonymous. Despues intentamos acceder mediante url
	 * al listado de shouts", y comprobamos que el resultado es una pagina de error, ya que no está autorizado.
	 */

	@Test
	public void listTaskNotEndedNegative() {

		this.signIn("administrator", "administrator");
		assert !super.exists(By.linkText("Anonymous"));
		super.driver.get("http://localhost:8090/Acme-Planner/anonymous/task/list");
		Assertions.assertEquals("Unexpected error", super.driver.findElement(By.xpath("/html/body/div[2]/div/h1")).getText());
		this.signOut();

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

