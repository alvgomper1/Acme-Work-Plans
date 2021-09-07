
package acme.testing.anonymous.task;

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

		super.setBaseCamp("http", "localhost", "8090", "/Acme-Work-Plans", "/master/welcome", "?language=en&debug=true");
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
 		super.driver.get("http://localhost:8090/Acme-Work-Plans/anonymous/task/list");
	 	this.checkPanicExists();
		this.signOut();

	}

	

}
