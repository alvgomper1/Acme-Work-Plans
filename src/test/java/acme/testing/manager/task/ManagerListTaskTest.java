package acme.testing.manager.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class ManagerListTaskTest extends AcmePlannerTest {
	
	/**
	 * La feature que prueba este test es la de listar tareas siendo un usuario manager
	 * <p>
	 * Este metodo accede al menu desplegable de manager y entra al listado
	 * 'List tasks'. Debe comprobar que el listado se muestra y no está vacio, para ello,
	 * se comprueba que en la tabla existen los valores correctos, comrobandolo con el archivo 'list-task-positive.csv'
	 * Ademas, comprueba que al clickar en una tarea determinado se muestran todos los datos correctamente
	 */  
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/list-task-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listTaskPositive(final int recordIndex, final String title, final String startDate, final String endDate, final String workload, 
		   				final String description, final String optionalLink, final String visibility, final String finished, final String executionPeriod) {		
		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "List tasks");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, startDate);
		super.checkColumnHasValue(recordIndex, 2, endDate);
		super.checkColumnHasValue(recordIndex, 3, workload);
		super.checkColumnHasValue(recordIndex, 4, visibility);
		super.checkColumnHasValue(recordIndex, 5, finished);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("optionalLink", optionalLink);
		super.fillInputBoxIn("visibility", visibility);
		int value = 0;
		if (finished == "true") value = 1;
		if (finished == "false") value = 0;
		super.fillInputBoxIn("finished", String.valueOf(value));
		super.checkInputBoxHasValue("executionPeriod", executionPeriod);
		
		super.signOut();
	}
	
	/**
	 * La feature que prueba este test es la de listar task como manager, pero el caso negativo, que seria intentando acceder al listado con un usuario sin 
	 * loguear
	 * Lo primero es comprobar que no existe la seccion de manager. Despues intentamos acceder mediante url
	 * al listado de tasks, y comprobamos que el resultado es una pagina de error, ya que no está autorizado.
	 */
	
	@Test
	public void listTasksManagerNegative() {

		super.navigateHome();
		assert !super.exists(By.linkText("Manager"));
		super.driver.get("http://localhost:8090/Acme-Planner/manager/task/list");
		Assertions.assertEquals("Unexpected error", super.driver.findElement(By.xpath("/html/body/div[2]/div/h1")).getText());
	}
}
