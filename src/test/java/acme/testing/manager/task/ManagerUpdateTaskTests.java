
package acme.testing.manager.task;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.springframework.core.annotation.Order;

import acme.testing.AcmeWorkPlansTest;

public class ManagerUpdateTaskTests extends AcmeWorkPlansTest {

	//Test cases------------------------------------------

	/**
	 * La feature que prueba este test es actualizar tareas siendo un usuario manager
	 * El test comprueba que se pueden actualizar cada uno de los campos en una multitud de tasks 
	 * sin que estos tengan ningún fallo.
	 * <p>
	 * Este metodo accede al menu desplegable de manager y entra al listado
	 * 'List tasks'. Debe comprobar que al actualizar los datos por otros de un fichero csv,
	 * se actualizan correctamente y sin fallos, comprobando todos los valores.
	 */

	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/update-positive.csv", encoding = "UTF-8", numLinesToSkip = 1)
	@Order(10)
	public void managerUpdateTaskPositive(final int recordIndex, final String title, final String start_date_time, final String end_date_time, final String workload, final String description, final String optional_link, final String visibility,
		final String finished) {

		super.signIn("manager1", "manager1");

		super.clickOnMenu("Manager", "List tasks");

		super.clickOnListingRecord(recordIndex);

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("startDate", start_date_time);
		super.fillInputBoxIn("endDate", end_date_time);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("optionalLink", optional_link);
		if (visibility.equals("Public"))
			super.fillInputBoxIn("visibility", "PUBLIC");
		if (visibility.equals("Private"))
			super.fillInputBoxIn("visibility", "PRIVATE");
		if (finished.equals("Finished"))
			super.fillInputBoxIn("finished", "1");
		if (finished.equals("Not finished"))
			super.fillInputBoxIn("finished", "0");
		super.clickOnSubmitButton("Update");

		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("startDate", start_date_time);
		super.checkInputBoxHasValue("endDate", end_date_time);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("optionalLink", optional_link);
		super.checkInputBoxHasValue("visibility", visibility);
		super.checkInputBoxHasValue("finished", finished);

		super.signOut();
	}

	/**
	 * La feature que prueba este test es actualizar tareas siendo un usuario manager,
	 * pero en este caso buscamos que falle. Para esto probamos a dejar un campo vacío 
	 * en cada una de las entradas del csv, probamos todas las formas de introducir 
	 * incorrectamente workload, probamos que las fechas no sean pasadas o que la fecha de 
	 * inicio sea despues de la fecha de fin y que el email tiene el formato correcto.
	 * <p>
	 * Este metodo accede al menu desplegable de manager y entra al listado
	 * 'List tasks'. Debe comprobar que al intentar actualizar la página devuelve error
	 * por los datos que se le están pasando.
	 */

	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/update-negative.csv", encoding = "UTF-8", numLinesToSkip = 1)
	@Order(20)
	public void managerUpdateTaskNegative(final int recordIndex, final String title, final String start_date_time, final String end_date_time, final String workload, final String description, final String optional_link, final String visibility,
		final String finished) {

		super.signIn("manager1", "manager1");

		super.clickOnMenu("Manager", "List tasks");

		super.clickOnListingRecord(recordIndex);

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("startDate", start_date_time);
		super.fillInputBoxIn("endDate", end_date_time);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("optionalLink", optional_link);
		if (visibility.equals("Public"))
			super.fillInputBoxIn("visibility", "PUBLIC");
		if (visibility.equals("Private"))
			super.fillInputBoxIn("visibility", "PRIVATE");
		if (visibility.equals("Finished"))
			super.fillInputBoxIn("visibility", "1");
		if (visibility.equals("Not finished"))
			super.fillInputBoxIn("visibility", "0");
		super.clickOnSubmitButton("Update");

		super.checkErrorsExist();

		super.signOut();
	}

	
	/**
	 * La feature que prueba este test es actualizar tareas siendo un usuario manager,
	 * la idea tras este test es que poniendo escenarios en lo que casi falla no se
	 * devuelve ningún fallo.
	 * <p>
	 * Este metodo accede al menu desplegable de manager y entra al listado
	 * 'List tasks'. Debe comprobar que al intentar actualizar la página devuelve error
	 * porque los datos que se le están pasando contienen palabras que superan el
	 * threshold de spam.
	 */

	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/spam-update-positive.csv", encoding = "UTF-8", numLinesToSkip = 1)
	@Order(30)
	public void managerUpdateTaskSpamPositive(final int recordIndex, final String title, final String description, final String optional_link) {

		//Gracias a esto siempre habrá el mismo threshold, que no dependerá del sample initial
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Spam Module");
		super.clear(By.id("threshold"));
		super.fill(By.id("threshold"), "30");
		super.clickAndGo(By.xpath("//*[@id=\"form\"]/button[1]"));

		super.signOut();
		//--------------------------------------------------------

		//Este es el test 
		super.signIn("manager1", "manager1");

		super.clickOnMenu("Manager", "List tasks");

		super.clickOnListingRecord(recordIndex);

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("optionalLink", optional_link);
		
		super.clickOnSubmitButton("Update");

		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("optionalLink", optional_link);

		super.signOut();
	}
	
	
	/**
	 * La feature que prueba este test es actualizar tareas siendo un usuario manager,
	 * pero en este caso buscamos que falle por parte del módulo de spam. Esto se hace
	 * poniendo una cantidad de palabras de spam sufiente en cada uno de los campos.
	 * <p>
	 * Este metodo accede al menu desplegable de manager y entra al listado
	 * 'List tasks'. Debe comprobar que al intentar actualizar la página devuelve error
	 * porque los datos que se le están pasando contienen palabras que superan el
	 * threshold de spam.
	 */

	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/spam-update-negative.csv", encoding = "UTF-8", numLinesToSkip = 1)
	@Order(40)
	public void managerUpdateTaskSpamNegative(final int recordIndex, final String title, final String description, final String optional_link) {

		//Gracias a esto siempre habrá el mismo threshold, que no dependerá del sample initial
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Spam Module");
		super.clear(By.id("threshold"));
		super.fill(By.id("threshold"), "30");
		super.clickAndGo(By.xpath("//*[@id=\"form\"]/button[1]"));

		super.signOut();
		//--------------------------------------------------------

		//Este es el test 
		super.signIn("manager1", "manager1");

		super.clickOnMenu("Manager", "List tasks");

		super.clickOnListingRecord(recordIndex);

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("optionalLink", optional_link);
		super.clickOnSubmitButton("Update");

		super.checkErrorsExist();

		super.signOut();
	}

}
