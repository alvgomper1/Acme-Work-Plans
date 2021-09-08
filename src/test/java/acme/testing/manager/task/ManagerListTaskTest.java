package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.components.UtilComponent;
import acme.testing.AcmeWorkPlansTest;

public class ManagerListTaskTest extends AcmeWorkPlansTest {
	
	/**
	 * La feature que prueba este test es la de listar tareas siendo un usuario manager
	 * <p>
	 * Este metodo accede al menu desplegable de manager y entra al listado
	 * 'List tasks'. Debe comprobar que el listado se muestra y no está vacio, para ello,
	 * se comprueba que en la tabla existen los valores correctos, comrobandolo con el archivo 'list-task-positive.csv'
	 * Ademas, comprueba que al clickar en una tarea determinado se muestran todos los datos correctamente. También se comprobará que al
	 * cambiar la página a español el formato de las fechas se convierta en "día/mes/año horas:minutos"
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
		if (visibility == "PUBLIC") super.checkInputBoxHasValue("visibility", "Public");
		if (visibility == "PRIVATE") super.checkInputBoxHasValue("visibility", "Private");
		if (finished == "true") super.checkInputBoxHasValue("finished", "Finished");
		if (finished == "false") super.checkInputBoxHasValue("finished", "Not finished");
		super.checkInputBoxHasValue("executionPeriod", executionPeriod);
		
		super.clickAndGo(By.xpath("/html/body/footer/div/div[3]/ul/li[2]/a")); //Spanish button
		super.clickOnMenu("Gerente", "Lista de tareas");
		super.checkColumnHasValue(recordIndex, 1, UtilComponent.formatDateStringToSpanish(startDate));
		super.checkColumnHasValue(recordIndex, 2, UtilComponent.formatDateStringToSpanish(endDate));
		
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
		super.driver.get("http://localhost:8090/Acme-Work-Plans/manager/task/list");
		this.checkPanicExists();
		}
}
