package acme.testing.manager.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class ManagerShowTaskTest extends AcmePlannerTest {

	/**
	 * La feature que prueba este test es la de mostrar tareas siendo un usuario manager
	 * <p>
	 * Este metodo accede al menu desplegable de manager y entra al listado
	 * 'List tasks'. Debe comprobar que al clickar en una tarea determinado se muestran todos los datos correctamente
	 */  
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/list-task-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void showTaskPositive(final int recordIndex, final String title, final String startDate, final String endDate, final String workload, 
		   				final String description, final String optionalLink, final String visibility, final String finished, final String executionPeriod) {		
		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "List tasks");
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("optionalLink", optionalLink);
		if (visibility == "PUBLIC") super.checkInputBoxHasValue("visibility", "Public");
		if (visibility == "PRIVATE") super.checkInputBoxHasValue("visibility", "Private");
		if (finished == "true") super.checkInputBoxHasValue("finished", "Finished");
		if (finished == "false") super.checkInputBoxHasValue("finished", "Not finished");
		super.checkInputBoxHasValue("executionPeriod", executionPeriod);
		
		super.signOut();
	}
	
	/**
	 * La feature que prueba este test es la de mostar task como manager, pero el caso negativo, que seria intentando acceder al listado con un usuario sin 
	 * loguear
	 * Lo primero es comprobar que no existe la seccion de manager. Despues intentamos acceder mediante url
	 * al listado de tasks, y comprobamos que el resultado es una pagina de error, ya que no está autorizado.
	 */
	
	@Test
	public void showTasksManagerNegative() {

		super.navigateHome();
		assert !super.exists(By.linkText("Manager"));
		super.driver.get("http://localhost:8090/Acme-Planner/manager/task/show?id=27");
		Assertions.assertEquals("Unexpected error", super.driver.findElement(By.xpath("/html/body/div[2]/div/h1")).getText());
	}
	
	/**
	 * La feature que prueba este test es la de mostar task como manager, pero el caso negativo, seria intentar acceder a una task que no existe
	 * Lo primero es comprobar que existe la seccion de manager una vez nos hemos logueado. Despues intentamos acceder mediante url
	 * a dicha task, y comprobamos que el resultado es una pagina de error, ya que esta no existe.
	 */
	
	@Test
	public void showTasksManagerNegative2() {

		super.navigateHome();
		this.signIn("manager1", "manager1");
		super.driver.get("http://localhost:8090/Acme-Planner/manager/task/show?id=67");
		Assertions.assertEquals("Unexpected error", super.driver.findElement(By.xpath("/html/body/div[2]/div/h1")).getText());
	}
}
 