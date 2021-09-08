package acme.testing.authenticated.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkPlansTest;
 
public class AuthenticatedTaskListTest extends AcmeWorkPlansTest{
	
	/**
	 * La feature que prueba este test es la de listar tareas publicas terminadas siendo un usuario authenticated, es decir, autenticado
	 * <p>
	 * Este metodo accede al menu desplegable de authenticated y entra al listado
	 * 'List tasks ended'. Debe comprobar que el listado se muestra y no está vacio, para ello,
	 * se comprueba que en la tabla existen los valores correctos, comrobandolo con el archivo 'list-task-positive.csv'
	 */  

	
	@ParameterizedTest 
	@CsvFileSource(resources = "/authenticated/task/list-task-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listTasksAuthenticatedPositive(final int recordIndex,final String endDate, final String executionPeriod, final String finished, 
		   				final String optionalLink,final String startDate, final String title, final String visibility, final String workload) {		
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Authenticated", "List tasks ended");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, startDate);
		super.checkColumnHasValue(recordIndex, 2, endDate);
		super.checkColumnHasValue(recordIndex, 3, workload);
		super.checkColumnHasValue(recordIndex, 4, visibility);
		super.checkColumnHasValue(recordIndex, 5, finished);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title );
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endDate", endDate );
		super.checkInputBoxHasValue("workload", workload );
		super.checkInputBoxHasValue("visibility", visibility );
		super.checkInputBoxHasValue("finished", finished );
		super.checkInputBoxHasValue("executionPeriod", executionPeriod );
		super.checkInputBoxHasValue("optionalLink", optionalLink );
		
		super.signOut();
	}
	
	/**
	 * La feature que prueba este test es la de listar task pblicas finalizadas como authenticated, pero el caso negativo, que seria intentando acceder al listado con un usuario sin 
	 * loguear
	 * Lo primero es comprobar que no existe la seccion de authenticated. Despues intentamos acceder mediante url
	 * al listado de tasks, y comprobamos que el resultado es una pagina de error, ya que no está autorizado.
	 */

	@Test
	public void listTasksAuthenticatedNegative() {

 		super.driver.get("http://localhost:8090/Acme-Work-Plans/authenticated/task/list");
 		this.checkPanicExists();
	}
}
