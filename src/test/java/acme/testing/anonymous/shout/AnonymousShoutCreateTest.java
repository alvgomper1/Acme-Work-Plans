package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest extends AcmePlannerTest{

	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-shout-spam-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createShoutPositive(final String recordIndex, final String author, final String info, final String text) {
		
		super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("text", text);
		
		super.clickOnSubmitButton("Shout!");
		
//		Comprobamos que no hay errores
		Assertions.assertFalse(super.exists(By.className("text-danger")));
		
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-shout-spam-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createShoutNegative(final String recordIndex, final String author, final String info, final String text) {
		
		super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("text", text);
		
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist();

		
	}
	
}
