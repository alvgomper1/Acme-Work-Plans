
package acme.testing.administrator.dashboard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AdministratorDashboardTests extends AcmePlannerTest {

	//Test cases--------------------------------------

	/**
	 * La feature que prueba este test es el dashboard de administrator con todas las 
	 * métricas de la página.
	 * <p>
	 * Una vez iniciada sesion como administrador, se accede a su menu desplegable y se entra
	 * al dashboard, donde se pueden ver todas las métricas organizadas.
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/dashboard/dashboard-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void administratorDashboardPositive(final int recordIndex, final String numberPublicTasks, final String numberPrivateTasks, final String numberFinishedTasks, final String numberNonFinishedTasks, final String averageTaskExecPeriod,
		final String deviationTaskExecPeriod, final String maxTaskExecPeriod, final String minTaskExecPeriod, final String averageTaskWorkload, final String deviationTaskWorkload, final String maxTaskWorkload, final String minTaskWorkload) {

		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Dashboard");

		Assertions.assertEquals(numberPublicTasks, super.locateOne(By.xpath("//div[2]/div/table[1]/tbody/tr[1]/td")).getText());
		Assertions.assertEquals(numberPrivateTasks, super.locateOne(By.xpath("//div[2]/div/table[1]/tbody/tr[2]/td")).getText());
		Assertions.assertEquals(numberFinishedTasks, super.locateOne(By.xpath("//div[2]/div/table[1]/tbody/tr[3]/td")).getText());
		Assertions.assertEquals(numberNonFinishedTasks, super.locateOne(By.xpath("//div[2]/div/table[1]/tbody/tr[4]/td")).getText());

		Assertions.assertEquals(averageTaskExecPeriod, super.locateOne(By.xpath("//div[2]/div/table[2]/tbody/tr[1]/td")).getText());
		Assertions.assertEquals(deviationTaskExecPeriod, super.locateOne(By.xpath("//div[2]/div/table[2]/tbody/tr[2]/td")).getText());
		Assertions.assertEquals(maxTaskExecPeriod, super.locateOne(By.xpath("//div[2]/div/table[2]/tbody/tr[3]/td")).getText());
		Assertions.assertEquals(minTaskExecPeriod, super.locateOne(By.xpath("//div[2]/div/table[2]/tbody/tr[4]/td")).getText());

		Assertions.assertEquals(averageTaskWorkload, super.locateOne(By.xpath("//div[2]/div/table[3]/tbody/tr[1]/td")).getText());
		Assertions.assertEquals(deviationTaskWorkload, super.locateOne(By.xpath("//div[2]/div/table[3]/tbody/tr[2]/td")).getText());
		Assertions.assertEquals(maxTaskWorkload, super.locateOne(By.xpath("//div[2]/div/table[3]/tbody/tr[3]/td")).getText());
		Assertions.assertEquals(minTaskWorkload, super.locateOne(By.xpath("//div[2]/div/table[3]/tbody/tr[4]/td")).getText());

		super.signOut();
	}

	/**
	 * La feature que prueba este test es el dashboard de administrator con todas las 
	 * métricas de la página pero en este caso no se podrá entrar ya que manager 
	 * no dispone de los permisos.
	 * <p>
	 * Una vez iniciada sesion como manaager, se intenta acceder al dashboard desde la url
	 * pero al no tener permisos nos devolverá un panic.
	 */
	
	@Test
	@Order(20)
	public void managerDashboardNegative() {

		super.navigateHome();
		super.signIn("manager1", "manager1");

		super.driver.get("http://localhost:8090/Acme-Planner/administrator/dashboard/show");
		super.checkPanicExists();
		
		super.signOut();
	}
	
	/**
	 * La feature que prueba este test es el dashboard de administrator con todas las 
	 * métricas de la página pero en este caso no se podrá entrar ya que anonymous 
	 * no dispone de los permisos.
	 * <p>
	 * Desde anonymous, se intenta acceder al dashboard desde la url pero al no tener 
	 * permisos nos devolverá un panic.
	 */
	
	@Test
	@Order(30)
	public void anonymousDashboardNegative() {

		super.navigateHome();

		super.driver.get("http://localhost:8090/Acme-Planner/administrator/dashboard/show");
		super.checkPanicExists();
		
	}
	

}
