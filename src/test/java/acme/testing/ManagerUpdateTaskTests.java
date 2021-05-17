package acme.testing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

public class ManagerUpdateTaskTests extends AcmePlannerTest{
	
	
	//Test cases------------------------------------------
	
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/update/positive.csv", encoding = "UTF-8", numLinesToSkip = 1)
	@Order(10)
	public void managerUpdateTaskPositive(final int recordIndex, final String title, final String start_date_time, final String end_date_time, final String workload, 
														final String description, final String optional_link, final String visibility, final String finished ) {
		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "List tasks");
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("startDate", start_date_time);
		super.fillInputBoxIn("endDate", end_date_time);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("optionalLink", optional_link);
//		super.fillInputBoxIn("visibility", visibility);
//		super.fillInputBoxIn("finished", finished);
		super.clickOnSubmitButton("Update");
		
//		super.clickOnMenu("Manager", "List tasks");
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("startDate", start_date_time);
		super.checkInputBoxHasValue("endDate", end_date_time);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("optionalLink", optional_link);
//		super.checkInputBoxHasValue("visibility", visibility);
//		super.checkInputBoxHasValue("finished", finished);
		
		super.signOut();		
	}
	
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/update/negative.csv", encoding = "UTF-8", numLinesToSkip = 1)
	@Order(20)
	public void managerUpdateTaskNegative(final int recordIndex, final String title, final String start_date_time, final String end_date_time, final String workload, 
														final String description, final String optional_link, final String visibility, final String finished ) {
		
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "List tasks");
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("startDate", start_date_time);
		super.fillInputBoxIn("endDate", end_date_time);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("optionalLink", optional_link);
//		super.fillInputBoxIn("visibility", visibility);
//		super.fillInputBoxIn("finished", finished);
		super.clickOnSubmitButton("Update");

		super.checkErrorsExist();
		
		super.signOut();		
	}
	
	
	
	
	
	
	

}
