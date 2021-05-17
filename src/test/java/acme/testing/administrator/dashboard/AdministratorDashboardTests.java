package acme.testing.administrator.dashboard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AdministratorDashboardTests extends AcmePlannerTest{

	
	//Test cases--------------------------------------
	
	
	@Test
	public void administratorDashboardPositive() {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Dashboard");
		
		
		final String numberPublicTasks = "12";
		final String numberPrivateTasks = "3";
		final String numberFinishedTasks = "6";
		final String numberNonFinishedTasks = "9";
		
		final String averageTaskExecPeriod = "292";
		final String deviationTaskExecPeriod = "277";
		final String maxTaskExecPeriod = "1176";
		final String minTaskExecPeriod = "48";
		
		final String averageTaskWorkload = "11.76";
		final String deviationTaskWorkload = "6.91";
		final String maxTaskWorkload = "20.00";
		final String minTaskWorkload = "0.20";
		
		
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
	
	
	
	
}
