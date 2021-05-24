package acme.testing.manager.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class ManagerCreateTaskTest extends AcmePlannerTest{
	
	/**
	 * La feature que prueba este test es la de crear una task sin spam como manager
	 * <p>
	 * Para ello iniciamos sesion como manager1 y entramos al formulario de creacion de task
	 * Seguidamente se comprueba que no hay errores en el formulario, entonces entramos al listado
	 * de task y comprobamos que la task que se ha creado tiene los datos correctos.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/create-task-spam-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createTaskPositive(final int recordIndex, final String description, final String end_date, final String optional_link, 
								   final String start_date, final String title, final String visibility, final String workload) {

		super.signIn("manager1", "manager1");

		super.clickOnMenu("Manager", "Create task");
		
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("endDate", end_date );
		super.fillInputBoxIn("optionalLink", optional_link);
		super.fillInputBoxIn("startDate", start_date);
		super.fillInputBoxIn("title", title );
		super.fillInputBoxIn("visibility", visibility);		
		super.fillInputBoxIn("workload", workload );
		
		super.clickOnSubmitButton("Create");
		Assertions.assertFalse(super.exists(By.className("text-danger")));
		
		super.clickOnMenu("Manager", "List tasks");
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("startDate", start_date);
		super.checkInputBoxHasValue("endDate", end_date);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("optionalLink", optional_link);
		if (visibility.equals("PUBLIC"))
			super.checkInputBoxHasValue("visibility", "Public");
		if (visibility.equals("PRIVATE"))
			super.checkInputBoxHasValue("visibility", "Private");

		super.signOut();
	}
	
	/**
	 * La feature que prueba este test es la de crear, sin exito, una task con campos vacios y errores como manager  
	 * <p>
	 * Para ello iniciamos sesion como manager1 y entramos al formulario de creacion de task
	 * Cuando estamos en el formulario de crear task, se introducen datos de un task con datos incorrectos.
	 * Se prueba a introducir un campo vacio por cada propiedad de la task, a excepción de la propiedad optionalLink, donde introducimos
	 * datos erroneos.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/create-task-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createTaskNegative(final int recordIndex, final String description, final String end_date,final String optional_link,
								   final String start_date, final String title, final String visibility, final String workload) {

		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Create task");
		
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("endDate", end_date );
		super.fillInputBoxIn("optionalLink", optional_link);
		super.fillInputBoxIn("startDate", start_date);
		super.fillInputBoxIn("title", title );
		super.fillInputBoxIn("visibility", visibility);		
		super.fillInputBoxIn("workload", workload );
		
		super.clickOnSubmitButton("Create");
		super.checkErrorsExist();
		
		super.signOut();
	}
	
	/**
	 * La feature que prueba este test es la de crear, sin exito, una task con spam como manager  
	 * <p>
	 * Para ello iniciamos sesion como manager1 y entramos al formulario de creacion de task
	 * Cuando estamos en el formulario de crear task, se introducen palabras de spam en los campos de descripcion, title y optional link
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/create-task-spam-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void createTaskNegativeSpam(final int recordIndex, final String description, final String end_date,final String optional_link,
								   final String start_date, final String title, final String visibility, final String workload) {

		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Create task");
		
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("endDate", end_date );
		super.fillInputBoxIn("optionalLink", optional_link);
		super.fillInputBoxIn("startDate", start_date);
		super.fillInputBoxIn("title", title );
		super.fillInputBoxIn("visibility", visibility);		
		super.fillInputBoxIn("workload", workload );
		
		super.clickOnSubmitButton("Create");
		super.checkErrorsExist();
		
		super.signOut();
	}

}
