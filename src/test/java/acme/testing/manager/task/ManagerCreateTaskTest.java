package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerCreateTaskTest extends AcmePlannerTest{

	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/create-task-spam-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createTaskPositive(final int recordIndex, final String description, final String end_date, final String execution_period, final String finished, 
							   final String optional_link, final String start_date, final String title, final String visibility, final String workload, final String manager_id) {

		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Create task");
		
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("end_date", end_date );
		super.fillInputBoxIn("optional_link", optional_link);
		super.fillInputBoxIn("start_date", start_date);
		super.fillInputBoxIn("title", title );
		super.fillInputBoxIn("visibility", visibility );
		super.fillInputBoxIn("workload", workload );
		
		super.checkNotErrorsExist();
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/create-task-spam-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createTaskNegative(final int recordIndex, final String description, final String end_date, final String execution_period, final String finished, 
							   final String optional_link, final String start_date, final String title, final String visibility, final String workload, final String manager_id) {

		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "Create task");
		
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("end_date", end_date );
		super.fillInputBoxIn("optional_link", optional_link);
		super.fillInputBoxIn("start_date", start_date);
		super.fillInputBoxIn("title", title );
		super.fillInputBoxIn("visibility", visibility );
		super.fillInputBoxIn("workload", workload );
		
		super.checkErrorsExist();
		
		super.signOut();
	}

}
