
package acme.testing.anonymous.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AnonymousTaskListTest extends AcmePlannerTest {

	@Override
	@BeforeAll
	public void beforeAll() {
		super.beforeAll();

		super.setBaseCamp("http", "localhost", "8090", "/Acme-Planner", "/master/welcome", "?language=en&debug=true");
		super.setAutoPausing(false);

	}

	/**
	 * La feature que prueba este test es la de listar y mostrar los detalles de las tareas no terminadas siendo un usuario anonymous, es decir, sin autenticar
	 * <p>
	 * Este metodo accede al menu desplegable de anonymous y entra al listado
	 * task not ended. Debe comprobar que el listado se muestra y no está vacio, para ello,
	 * se comprueba que en la tabla hay filas con la etiqueta
	 * 'td' y la clase "control.sorting_1" o que no existe la clase "dataTables_empty", ya que esto significa que
	 * no hay tasks disponibles en la lista.
	 * Tambien se hace la comprobacion de que la variable finished y public de la task cumple con la restriccion de ser false y PUBLIC
	 * para cada caso
	 */

	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/task/tasks.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void listAndShowTasksAnonymous(final int recordIndex, final String title, final String startDate, final String endDate, final String workload, final String description,
		final String optionalLink,  final String visibility, final String executionPeriod, final String finished) {		
		
		super.navigateHome();
		super.clickAndGo(By.linkText("Anonymous"));
		

		super.clickOnLink("List tasks not ended");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, startDate);
		super.checkColumnHasValue(recordIndex, 2, endDate);
		super.checkColumnHasValue(recordIndex, 3, workload);
		super.checkColumnHasValue(recordIndex, 4, visibility);
		super.checkColumnHasValue(recordIndex, 5, finished);
		
		

		//Con clickOnListingRecord accedemos al metodo de mostrar detalles de las tareas públicas no terminadas y comprobamos que cada atributo corresponde a su tarea
		
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("optionalLink", optionalLink);
		super.checkInputBoxHasValue("visibility", visibility);
		super.checkInputBoxHasValue("executionPeriod", executionPeriod);
		super.checkInputBoxHasValue("finished", finished);

	}
	/**
	 * La feature que prueba este test es la de listar tareas como anonymous, pero el caso negativo, que seria intentando acceder al listado con un usuario logueado con
	 * el rol de Administrador
	 * <p>
	 * Una vez loguado como administrator, lo primero es comprobar que no existe la seccion de anonymous. Despues intentamos acceder mediante url
	 * al listado de tareas", y comprobamos que el resultado es una pagina de error, ya que no está autorizado.
	 */

	@Test
	public void listTasksAnonymousNegative() {

		super.signIn("administrator", "administrator");
 		super.driver.get("http://localhost:8090/Acme-Planner/anonymous/task/list");
	 	Assertions.assertEquals("Unexpected error", super.driver.findElement(By.xpath("/html/body/div[2]/div/h1")).getText());
		 
		this.signOut();

	}
	
	/**
	 * La feature que prueba este test es la de mostrar los detalles de las tareas como anonymous, pero el caso negativo, que seria intentando acceder al listado con un usuario logueado con
	 * el rol de Administrador
	 * <p>
	 * Una vez loguado como administrator, lo primero es comprobar que no existe la seccion de anonymous. Despues intentamos acceder mediante url
	 * a los detalles de la tarea", y comprobamos que el resultado es una pagina de error, ya que no está autorizado.
	 */
	
	@Test
	public void showTasksAnonymousNegativeSignIn() {

		super.signIn("administrator", "administrator");
 		super.driver.get("http://localhost:8090/Acme-Planner/anonymous/task/show?id=35");
	 	Assertions.assertEquals("Unexpected error", super.driver.findElement(By.xpath("/html/body/div[2]/div/h1")).getText());
		 
		this.signOut();

	}
	
	/**
	 * La feature que prueba este test es la de mostrar los detalles de las tareas como anonymous, pero el caso negativo, que seria intentando acceder a una tarea
	 * cuyo id no existe.
	 * <p>
	 * Intentamos acceder mediante url a los detalles de la tarea imponiendo una id que no existe, en este caso la letra s", y comprobamos que el resultado es una pagina de error..
	 */
	
	@Test
	public void showTasksAnonymousNegativeWrongTaskId() {

 		super.driver.get("http://localhost:8090/Acme-Planner/anonymous/task/show?id=s");
	 	Assertions.assertEquals("Unexpected error", super.driver.findElement(By.xpath("/html/body/div[2]/div/h1")).getText());
		
	}
	
	/**
	 * La feature que prueba este test es la de mostrar los detalles de las tareas como anonymous, pero el caso negativo, que seria intentando acceder a una tarea
	 * privada, lo cual no es posible de hacer en esta feature.
	 * <p>
	 * Intentamos acceder mediante url a los detalles de la tarea imponiendo una id de una tarea privada, en este caso la tarea 27", y comprobamos que el resultado es una pagina de error..
	 */
	@Test
	public void showTasksAnonymousNegativePrivateTask() {

 		super.driver.get("http://localhost:8090/Acme-Planner/anonymous/task/show?id=27");
	 	Assertions.assertEquals("Unexpected error", super.driver.findElement(By.xpath("/html/body/div[2]/div/h1")).getText());
		
	}
	
	/**
	 * La feature que prueba este test es la de mostrar los detalles de las tareas como anonymous, pero el caso negativo, que seria intentando acceder a una tarea
	 * finalizada, lo cual no es posible de hacer en esta feature.
	 * <p>
	 * Intentamos acceder mediante url a los detalles de la tarea imponiendo una id de una tarea finalizada, en este caso la tarea 29", y comprobamos que el resultado es una pagina de error..
	 */
	@Test
	public void showTasksAnonymousNegativeFinishedTask() {

 		super.driver.get("http://localhost:8090/Acme-Planner/anonymous/task/show?id=29");
	 	Assertions.assertEquals("Unexpected error", super.driver.findElement(By.xpath("/html/body/div[2]/div/h1")).getText());
		
	}

	

}
