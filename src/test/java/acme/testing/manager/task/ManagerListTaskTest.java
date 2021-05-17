package acme.testing.manager.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class ManagerListTaskTest extends AcmePlannerTest {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/list-task-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listTaskPositive(final int recordIndex, final String title, final String start_date, final String end_date, final String workload, 
		   				final String description, final String optional_link, final String visibility, final String finished, final String execution_period) {		
		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "List tasks");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, start_date);
		super.checkColumnHasValue(recordIndex, 2, end_date);
		super.checkColumnHasValue(recordIndex, 3, workload);
		super.checkColumnHasValue(recordIndex, 4, visibility);
		super.checkColumnHasValue(recordIndex, 5, finished);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("startDate", start_date);
		super.checkInputBoxHasValue("endDate", end_date);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("optionalLink", optional_link);
//		super.checkInputBoxHasValue("visibility", visibility);
//		super.checkInputBoxHasValue("finished", finished);
		super.checkInputBoxHasValue("executionPeriod", execution_period);
		
		super.signOut();
	}
	
	@Test
	public void listTasksManagerNegative() {

		super.navigateHome();
		assert !super.exists(By.linkText("Manager"));
		super.driver.get("http://localhost:8090/Acme-Planner/manager/task/list");
		Assertions.assertEquals("Unexpected error", super.driver.findElement(By.xpath("/html/body/div[2]/div/h1")).getText());
	}
}
