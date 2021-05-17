
package acme.testing.anonymous.task;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import acme.testing.AcmePlannerTest;

public class AnonymousTaskListTest extends AcmePlannerTest {

	@Override
	@BeforeAll
	public void beforeAll() {
		super.beforeAll();

		super.setBaseCamp("http", "localhost", "8090", "/Acme-Planner", "/master/welcome", "?language=en&debug=true");
		super.setAutoPausing(false);
//		this.signIn("administrator", "administrator");
//		super.clickAndGo(By.linkText("Administrator"));
//		super.clickOnLink("Populate DB (initial)");
//		this.navigateHome();
//		super.clickAndGo(By.linkText("Administrator"));
//		super.clickOnLink("Populate DB (samples)");
//		this.signOut();

	}

	/**
	 * La feature que prueba este test es la de listar tareas no terminadas siendo un usuario anonymous, es decir, sin autenticar
	 * <p>
	 * Este metodo accede al menu desplegable de anonymous y entra al listado
	 * task not ended. Debe comprobar que el listado se muestra y no está vacio, para ello,
	 * se comprueba que en la tabla hay filas con la etiqueta
	 * 'td' y la clase "control.sorting_1" o que no existe la clase "dataTables_empty", ya que esto significa que
	 * no hay tasks disponibles en la lista.
	 * Tambien se hace la comprobacion de que la variable finished y public de la task cumple con la restriccion de ser false y PUBLIC
	 * para cada caso
	 */

	@Test
	public void listTasksAnonymous() {

		//Accedemos al listado de tareas no finalizadas de Anonymous
		super.navigateHome();
		super.clickAndGo(By.linkText("Anonymous"));

		super.clickOnLink("List tasks not ended");

		//Se comprueba que se muestra el listado con o sin datos
		Assertions.assertTrue(super.exists(By.cssSelector(".control.sorting_1")) || super.exists(By.className("dataTables_empty")));

		//Si hay datos en el listado, recorremos los atributos finished y visibility
		if (super.exists(By.cssSelector(".control.sorting_1"))) {
			final List<WebElement> elements = super.driver.findElements(By.cssSelector(".control.sorting_1"));

			for (int i = 1; i <= elements.size(); i++) {
				final String finished = super.driver.findElement(By.xpath("//*[@id=\"list\"]/tbody/tr["+i+"]/td[7]")).getText();
				final String visibility = super.driver.findElement(By.xpath("//*[@id=\"list\"]/tbody/tr[1]/td[6]")).getText();
				//Comprobacion de la restriccion de la variable momento
				Assertions.assertEquals("false", finished);
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
	public void listTasksAnonymousNegative() {

		super.signIn("administrator", "administrator");
 		super.driver.get("http://localhost:8090/Acme-Planner/anonymous/task/list");
	 	Assertions.assertEquals("Unexpected error", super.driver.findElement(By.xpath("/html/body/div[2]/div/h1")).getText());
		 
		this.signOut();

	}

	

}
