package acme.testing.authenticated.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedListPublicFinishedTaskTest extends AcmePlannerTest{

	
	/**
	 * La feature que prueba este test es la de mostrar los detalles de tareas publicas finalizadas para principal authenticated
	 * <p>
	 * Para ello, iniciamos sesion y accedemos al listado.
	 * Cuando estamos en el listado pulsamos en una task y comprobamos que los 
	 * atributos de cada task coinciden con las 5 primeras del csv
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/task/list-task-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listTaskPositive(final int recordIndex,final String end_date, final String execution_period, final String finished, 
		   				final String optional_link,final String start_date, final String title, final String visibility, final String workload) {		
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Authenticated", "List tasks ended");
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title );
		super.checkInputBoxHasValue("startDate", start_date);
		super.checkInputBoxHasValue("endDate", end_date );
		super.checkInputBoxHasValue("workload", workload );
		super.checkInputBoxHasValue("visibility", visibility );
		super.checkInputBoxHasValue("finished", finished );
		super.checkInputBoxHasValue("executionPeriod", execution_period );
		super.checkInputBoxHasValue("optionalLink", optional_link );
		
		super.signOut();
	}
	@Test
	public void listTaskNegative() {

		super.navigateHome();		
		super.driver.get("http://localhost:8090/Acme-Planner/manager/task/list");
		this.checkPanicExists();
		}
	
}
