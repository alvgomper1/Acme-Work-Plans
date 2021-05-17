package acme.testing.authenticated.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AuthenticatedListPublicFinishedTaskTest extends AcmePlannerTest{

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/task/list-task-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listTaskPositive(final int recordIndex,final String end_date, final String execution_period, final String finished, 
		   				final String optional_link, final String start_date, final String title, final String visibility, final String workload) {		
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Authenticated", "List tasks ended");
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title );
		super.checkInputBoxHasValue("startDate", start_date);
		super.checkInputBoxHasValue("endDate", end_date );
		super.checkInputBoxHasValue("workload", workload );
		super.checkInputBoxHasValue("visibility", visibility );
		super.checkInputBoxHasValue("finished", finished );

		super.signOut();
	}
	
}
