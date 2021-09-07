package acme.testing.authenticated.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AuthenticatedShowPublicFinishedTaskTest extends AcmePlannerTest{

	
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
	
	/**
	 * La feature que prueba este test es la de mostrar los detalles de tareas publicas finalizadas para principal authenticated.
	 * <p>
	 * Se quiere comprobar que al cambiar de idioma hacia el español, el formato de las fechas cambia a "día/mes/año horas:minutos"
	 * , coincidiendo con el CVS
	 * Para ello, iniciamos sesion y accedemos al listado.
	 * Cuando estamos en el listado pulsamos en una task y comprobamos que los 
	 * atributos de cada task coinciden con las 5 primeras del csv
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/task/list-task-positive-spanish.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void listTaskPositiveSpanish(final int recordIndex,final String end_date, final String execution_period, final String finished, 
		   				final String optional_link,final String start_date, final String title, final String visibility, final String workload) {		
	
		super.signIn("administrator", "administrator");
		
		super.clickAndGo(By.xpath("/html/body/footer/div/div[3]/ul/li[2]/a"));
		super.clickOnMenu("Autenticado", "Lista de tareas terminadas");
		
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
	
	
	/**
	 * La feature que prueba este test es la intentar acceder al listado como usuario no autenticado
	 * <p>
	 * Accedemos sin autenticarnos por URL al listado de task, como no está autenticado, salta un error panic
	 */
	@Test
	public void listTaskNegative() {
		super.navigateHome();		
		super.driver.get("http://localhost:8090/Acme-Work-Plans/manager/task/list");
		this.checkPanicExists();
	}
	
}
