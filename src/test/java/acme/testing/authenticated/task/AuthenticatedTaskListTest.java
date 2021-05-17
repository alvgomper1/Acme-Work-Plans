package acme.testing.authenticated.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedTaskListTest extends AcmePlannerTest{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/task/list-task-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listTaskPositive(final int recordIndex,final String end_date, final String execution_period, final String finished, 
		   				final String optional_link,final String start_date, final String title, final String visibility, final String workload) {		
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Authenticated", "List tasks ended");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, start_date);
		super.checkColumnHasValue(recordIndex, 2, end_date);
		super.checkColumnHasValue(recordIndex, 3, workload);
		super.checkColumnHasValue(recordIndex, 4, visibility);
		super.checkColumnHasValue(recordIndex, 5, finished);
		
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
}
